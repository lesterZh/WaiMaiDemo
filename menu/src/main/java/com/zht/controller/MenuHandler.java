package com.zht.controller;

import com.zht.entity.Menu;
import com.zht.entity.MenuVO;
import com.zht.entity.Type;
import com.zht.repository.MenuRepository;
import com.zht.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/index")
    public String index() {
        return port;
    }

    @GetMapping("/findAllMenu/{page}/{limit}")
    public MenuVO findAll(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        List<Menu> list = menuRepository.findAll(page, limit);
        MenuVO menuVO = new MenuVO(0, "", menuRepository.count(), list);

        return menuVO;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") long id) {
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes() {
        return typeRepository.findTypes();
    }

    @PostMapping("/save")
    public void save(@RequestBody Menu menu) {
        System.out.println("save:" + menu);
        menuRepository.save(menu);
    }

    @PostMapping("/update")
    public void update(@RequestBody Menu menu) {
        System.out.println("update:" + menu);
        menuRepository.update(menu);
    }

    @GetMapping("find/{id}")
    public Menu findById(@PathVariable("id") long id) {
        return menuRepository.findById(id);
    }
}
