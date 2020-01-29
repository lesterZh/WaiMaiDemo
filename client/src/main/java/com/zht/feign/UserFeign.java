package com.zht.feign;

import com.zht.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user")
public interface UserFeign {

    @GetMapping("/user/find/all/{page}/{limit}")
    public List<User> findAll(@PathVariable("page") int page, @PathVariable("limit") int limit);

    @GetMapping("/user/find/{id}")
    public User findById(@PathVariable("id") long id);

    @GetMapping("/user/count")
    public int count();

    @DeleteMapping("/user/delete/{id}")
    public void deleteById(@PathVariable("id") long id);

    @PostMapping("/user/save")
    public void save(@RequestBody User user);

    @PostMapping("/user/update")
    public void update(@RequestBody User user);
}
