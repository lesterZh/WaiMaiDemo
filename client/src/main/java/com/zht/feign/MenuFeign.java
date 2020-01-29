package com.zht.feign;

import com.zht.entity.Menu;
import com.zht.entity.MenuVO;
import com.zht.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("menu")
public interface MenuFeign {

    @RequestMapping("/menu/findAllMenu/{page}/{limit}")
    public MenuVO findAll(@PathVariable("page") int page, @PathVariable("limit") int limit);

    @DeleteMapping("/menu/delete/{id}")
    public void deleteMenuById(@PathVariable("id") long id);

    @GetMapping("/menu/findTypes")
    public List<Type> findTypes();

    @PostMapping("/menu/save")
    public void save(@RequestBody Menu menu);

    @PostMapping("/menu/update")
    public void update(@RequestBody Menu menu);

    @GetMapping("/menu/find/{id}")
    public Menu findById(@PathVariable("id") long id);
}
