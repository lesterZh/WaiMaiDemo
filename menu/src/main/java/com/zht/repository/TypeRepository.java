package com.zht.repository;

import com.zht.entity.Menu;
import com.zht.entity.Type;

import java.util.List;

public interface TypeRepository {
    public Type findById(long id);
    public List<Type> findTypes();
}
