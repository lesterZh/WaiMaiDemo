package com.zht.controller;


import com.zht.entity.Order;
import com.zht.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/findAllByUid/{page}/{limit}/{uid}")
    public List<Order> findAllByUid(@PathVariable("page") int page, @PathVariable("limit") int limit,
                                    @PathVariable("uid") String uid) {
        log("find all", page+"", limit+"");
        List<Order> orders = orderRepository.findAllByUid(page, limit, uid);
        return orders;

    }

    @PostMapping(value = "/save")
    public void save(@RequestBody Order order) {
        log("save", order.toString());
        orderRepository.save(order);
    }

    @GetMapping("/count/{uid}")
    public int countByUid(@PathVariable("uid") String uid) {
        return orderRepository.countByUid(uid);
    }

    @GetMapping("/findAllByState/{page}/{limit}/{state}")
    public List<Order> findAllByState(@PathVariable("page") int page, @PathVariable("limit") int limit,
                                    @PathVariable("state") int state) {
        return orderRepository.findAllByState(page, limit, state);
    }

    @GetMapping("/updateStateTo1/{id}")
    public void updateStateTo1(@PathVariable("id") long id) {
        orderRepository.updateStateTo1(id);
    }

    @GetMapping("/countByState0")
    public int countByState0() {
        return orderRepository.countByState(0);
    }

    public static void log(String... msg) {
        StringBuilder sb = new StringBuilder();

        for (String v : msg) {
            sb.append(v).append(",");
        }
        System.out.println(sb.toString());
    }
}
