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

    public long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getMid() {
        return mid;
    }

    public String getAid() {
        return aid;
    }

    public Date getDate() {
        return date;
    }

    public int getState() {
        return state;
    }
}
