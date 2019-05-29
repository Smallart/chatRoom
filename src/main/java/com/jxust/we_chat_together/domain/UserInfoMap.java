package com.jxust.we_chat_together.domain;

import java.util.Iterator;
import java.util.List;

public class UserInfoMap {
    private Integer fromId;
    private List<UserInfo> infoList;

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public List<UserInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<UserInfo> infoList) {
        this.infoList = infoList;
    }

    @Override
    public String toString() {
        return "UserInfoMap{" +
                "fromId=" + fromId +
                ", infoList=" + infoList +
                '}';
    }
}
