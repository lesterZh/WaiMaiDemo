package com.zht.controller;

import com.zht.entity.User;
import com.zht.entity.UserVO;
import com.zht.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/index")
    public String index(Model model) {
        return "user_manage";
    }

    @GetMapping("/add")
    public String userAdd() {
        log("add html");
        return "user_add";
    }

    @GetMapping("/find/all")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        log("find all:" + page + "," + limit);
        UserVO userVO = new UserVO(0,"", userFeign.count(), userFeign.findAll(page, limit));
        return userVO;
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") long id) {
        log("find by id:" + id);
        return userFeign.findById(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        log("delete by id:" + id);
        userFeign.deleteById(id);
        return "redirect:/user/index";
    }

    @PostMapping("/save")
    public String save(User user) {
        log("save:" + user.toString());
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/user/index";
    }

    @PostMapping("/update")
    public String update(@RequestBody User user) {
        log("update:" + user);
        userFeign.update(user);
        return "redirect:/user/index";
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}
