package com.zht.repository;

import com.zht.entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findAllByUid(int index, int limit, String uid);
    public int countByUid(String uid);
    public int countByState(int state);
    public List<Order> findAllByState(int index, int limit, int state);
    public void updateStateTo1(long id);
}
