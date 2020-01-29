package com.zht.repository;

import com.zht.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
