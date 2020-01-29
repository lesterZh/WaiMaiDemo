package com.zht.controller;

import com.zht.entity.Menu;
import com.zht.entity.MenuVO;
import com.zht.entity.Type;
import com.zht.feign.MenuFeign;
import com.zht.utils.MyUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    MenuFeign menuFeign;

    @RequestMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        return menuFeign.findAll(index, limit);
    }

    @RequestMapping("/index")
    public String index() {
        return "menu_manage";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMenuById(@PathVariable("id") long id) {
        System.out.println("delete:" + id);
        menuFeign.deleteMenuById(id);
        return "redirect:/menu/index";
    }

    @RequestMapping("/add")
    public ModelAndView addMenu() {
        MyUtil.log("start add");
        ModelAndView view = new ModelAndView();
        view.setViewName("menu_add");
        view.addObject("list", menuFeign.findTypes());
        return view;
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request) throws IOException {
        Reader reader = request.getReader();
        List<String> body = IOUtils.readLines(reader);


        System.out.println("body:"+ body);
        Menu menu = convertToMenu(body);
        System.out.println("save:" + menu);

        menuFeign.save(menu);
        return "redirect:/menu/index";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request) throws IOException {
        MyUtil.log("update:" + request.getContentLength());
        Reader reader = request.getReader();
        List<String> body = IOUtils.readLines(reader);
        System.out.println("body:"+ body);
        Menu menu = convertToMenu(body);
        System.out.println("update:" + menu);
        menuFeign.update(menu);
        return "redirect:/menu/index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");
        Menu menu = menuFeign.findById(id);
        modelAndView.addObject("menu", menuFeign.findById(id));
        modelAndView.addObject("list", menuFeign.findTypes());
        return modelAndView;
    }

    public Menu convertToMenu(List<String> body) {
        Menu menu = new Menu();
        Map<String, String> map = new HashMap<>();
        for (String date: body) {
            String[] ds = date.split("=");
            if (ds != null && ds.length == 2) {
                map.put(ds[0], ds[1]);
            }
        }
        menu.setName(map.get("name"));
        menu.setFlavor(map.get("flavor"));
        menu.setPrice(Double.parseDouble(map.get("price")));
        menu.setType(new Type(Long.parseLong(map.get("type.id")), null));
        if (map.containsKey("id")) {
            menu.setId(Long.parseLong(map.get("id")));
        }
        return menu;
    }

}
