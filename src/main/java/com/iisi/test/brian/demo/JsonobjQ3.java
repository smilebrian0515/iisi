package com.iisi.test.brian.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JsonobjQ3 {
    int userId;
    String name;
    Date loginTime;

    @Override
    public String toString() {
        return "JsonobjQ3{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
