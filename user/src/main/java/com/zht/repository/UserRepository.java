package com.zht.repository;

import com.zht.entity.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll(int page, int limit);
    public User findById(long id);
    public void deleteById(long id);
    public int count();
    public void save(User user);
    public void update(User user);
}
