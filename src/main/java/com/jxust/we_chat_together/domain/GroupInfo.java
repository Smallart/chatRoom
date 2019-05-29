package com.jxust.we_chat_together.domain;

import java.util.Date;

public class GroupInfo {
    //ID
    private Integer id;
    //用户的ID
    private Integer userId;
    //群组的ID
    private Integer groupId;
    //用户在群组发送的消息
    private String message;
    //发送消息的时间
    private Date infoTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }
}
