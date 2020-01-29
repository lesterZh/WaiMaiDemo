package com.zht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class OrderWrapper {
    private long id;
    private String uid;
    private String mid;
    private String aid;
    private Date date;
    private int state;
    private Menu menu;
    private User user;

    public OrderWrapper(Order order) {
        this.id = order.getId();
        this.uid = order.getUid();
        this.mid = order.getMid();
        this.aid = order.getAid();
        this.date = order.getDate();
        this.state = order.getState();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(int state) {
        this.state = state;
    }
}
