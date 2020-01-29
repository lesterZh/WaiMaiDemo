package com.zht.controller;

import com.zht.entity.User;
import com.zht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "port:" + port;
    }

    @GetMapping("/find/all/{page}/{limit}")
    public List<User> findAll(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        log("find all:" + page + "," + limit);
        int start = (page - 1) * limit;
        return userRepository.findAll(start, limit);
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") long id) {
        log("find by id:" + id);
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        log("count");
        return userRepository.count();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        log("delete by id:" + id);
        userRepository.deleteById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        log("save:" + user);
        userRepository.save(user);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {
        log("update:" + user);
        userRepository.update(user);
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}
