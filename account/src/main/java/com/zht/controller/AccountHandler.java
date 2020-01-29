package com.zht.controller;

import com.zht.repository.AdminRepository;
import com.zht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username, @PathVariable("password") String password,
                        @PathVariable("type") String type) {
        log("login:" , username, password);
        Object ret = null;

        if (type.equals("user")) {
            ret = userRepository.login(username, password);
        } else if (type.equals("admin")) {
            ret = adminRepository.login(username, password);
        }

        return ret;
    }


    public static void log(String... msg) {
        StringBuilder sb = new StringBuilder();

        for (String v : msg) {
            sb.append(v).append(",");
        }
        System.out.println(sb.toString());
    }
}
