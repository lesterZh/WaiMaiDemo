package com.zht.repository;

import com.zht.entity.Admin;
import com.zht.entity.User;

public interface UserRepository {
    public User login(String username, String password);
}
