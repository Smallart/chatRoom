package com.jxust.we_chat_together.domain;

import java.util.Date;

public class UserInfo {
    private Integer id;
    private String message;
    private Date infotime;
    private Integer state;

    public UserInfo() {
    }

    public UserInfo(String message, Date infotime, Integer state) {
        this.message = message;
        this.infotime = infotime;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getInfotime() {
        return infotime;
    }

    public void setInfotime(Date infotime) {
        this.infotime = infotime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", infotime=" + infotime +
                ", state=" + state +
                '}';
    }
}
