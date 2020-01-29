package com.zht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Type {
    private long id;
    private String name;

    public Type(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
