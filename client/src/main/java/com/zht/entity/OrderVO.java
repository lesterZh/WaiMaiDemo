package com.zht.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private int code = 0;
    private String msg = "";
    private int count = 100;
    private List<OrderWrapper> data;

    public OrderVO(int code, String msg, int count, List<OrderWrapper> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<OrderWrapper> getData() {
        return data;
    }

    public void setData(List<OrderWrapper> data) {
        this.data = data;
    }
}
