package com.zht.feign;

import com.zht.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("order")
public interface OrderFeign {
    @PostMapping(value = "/order/save")
    public void save(@RequestBody Order order);

    @GetMapping("/order/findAllByUid/{page}/{limit}/{uid}")
    public List<Order> findAllByUid(@PathVariable("page") int page, @PathVariable("limit") int limit,@PathVariable("uid") String uid);

    @GetMapping("/order/count/{uid}")
    public int countByUid(@PathVariable("uid") String uid);

    @GetMapping("/order/updateStateTo1/{id}")
    public void updateStateTo1(@PathVariable("id") long id);

    @GetMapping("/order/findAllByState/{page}/{limit}/{state}")
    public List<Order> findAllByState(@PathVariable("page") int page, @PathVariable("limit") int limit,
                                      @PathVariable("state") int state);

    @GetMapping("/order/countByState0")
    public int countByState0() ;
}
