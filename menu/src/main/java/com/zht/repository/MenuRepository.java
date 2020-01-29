package com.zht.repository;

import com.zht.entity.Menu;

import java.util.List;

public interface MenuRepository {
    public List<Menu> findAll(int page, int limit);
    public int count();
    public Menu findById(long id);
    public void save(Menu menu);
    public void update(Menu menu);
    public void deleteById(long id);
}
