package com.zht.controller;

import com.google.gson.Gson;
import com.zht.entity.Admin;
import com.zht.entity.User;
import com.zht.feign.AccountFeign;
import com.zht.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/do/login")
    public String doLogin(String username, String password, String type, Model model, HttpSession session) {
        log("login:", username, password, type);
        Object obj = accountFeign.login(username, password, type);

        if (obj == null) {
            model.addAttribute("msg", "用户名或者密码错误");
            return "login";
        }
        log("get account:", obj.toString(), (obj instanceof User)+"", (obj instanceof Admin)+"");

        Map<String, Object> map = (Map<String, Object>) obj;
        long id = Long.parseLong(map.get("id") + "");

        if (type.equals("user")) {
            User user = userFeign.findById(id);
            log(user.toString());
            session.setAttribute("user", user);
            return "index";
        } else if (type.equals("admin")) {
            Admin admin = new Admin();
            admin.setId(id);
            admin.setUsername(map.get("username").toString());
            session.setAttribute("admin", admin);
            return "main";
        }

        return "redirect:/account/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
//        session.removeAttribute("user");
//        session.removeAttribute("admin");
        session.invalidate();
        return "redirect:/account/login";
    }

    public static void log(String... msg) {
        StringBuilder sb = new StringBuilder();

        for (String v : msg) {
            sb.append(v).append(",");
        }
        System.out.println(sb.toString());
    }
}
