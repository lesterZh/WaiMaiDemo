package com.zht.controller;

import com.zht.entity.*;
import com.zht.feign.MenuFeign;
import com.zht.feign.OrderFeign;
import com.zht.feign.UserFeign;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private MenuFeign menuFeign;

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/index")//针对普通用户的订单查看页面
    public String index() {
        return "order";
    }

    @GetMapping("/manage")//针对管理员用户的订单查看页面
    public String manage() {
        return "order_handler";
    }

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session ) {
        Order order = new Order();
        User user = (User) session.getAttribute("user");
        order.setUid(user.getId()+"");
        order.setMid(mid + "");
        order.setState(0);
        order.setAid(1+"");
        order.setDate(new Date());
        log("order save", mid+"");
        orderFeign.save(order);

        return "index";
    }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String uid = user.getId() + "";
        int index = (page - 1) * limit;
        List<Order> list = orderFeign.findAllByUid(index, limit,uid);
        log(uid, list.toString());
        int count = orderFeign.countByUid(uid);
        OrderVO orderVO = new OrderVO(0, "", count, convertToWrapper(list, false));
        log(count+"", orderVO.toString());
        return orderVO;
    }

    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        List<Order> list = orderFeign.findAllByState(index, limit,0);
        log(list.toString());
        int count = orderFeign.countByState0();
        OrderVO orderVO = new OrderVO(0, "", count, convertToWrapper(list, true));
        log(count+"", orderVO.toString());
        return orderVO;
    }

    @GetMapping("/updateStateTo1/{id}")
    public String updateStateTo1(@PathVariable("id") long id) {
        orderFeign.updateStateTo1(id);
        return "redirect:/order/manage"; //刷新页面
    }


    private List<OrderWrapper> convertToWrapper(List<Order> list, boolean setUser) {
        List<OrderWrapper> wrappers = new ArrayList<>();

        for (Order order : list) {
            OrderWrapper wrapper = new OrderWrapper(order);
            Menu menu = menuFeign.findById(Long.parseLong(order.getMid()));
            if (menu == null) {
                menu = new Menu();
            }
            log(menu.toString(), (menu instanceof Menu) + "");
            wrapper.setMenu(menu);

            if (setUser) {
                User user = userFeign.findById(Long.parseLong(order.getUid()));
                wrapper.setUser(user);
            }

            wrappers.add(wrapper);
        }

        return wrappers;
    }


    public static void log(String... msg) {
        StringBuilder sb = new StringBuilder();

        for (String v : msg) {
            sb.append(v).append(",");
        }
        System.out.println(sb.toString());
    }
}
