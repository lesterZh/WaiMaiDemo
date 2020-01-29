package com.zht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private String uid;
    private String mid;
    private String aid;
    private Date date;
    private int state;
}
