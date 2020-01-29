package com.zht.entity;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Menu {
    private long id=0;
    private String name="default";
    private double price=0;
    private String flavor="default";
    private Type type;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
