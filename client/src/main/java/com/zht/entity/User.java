package com.zht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private String telephone;
    private Date registerdate;
    private String address;

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public String getAddress() {
        return address;
    }
}
