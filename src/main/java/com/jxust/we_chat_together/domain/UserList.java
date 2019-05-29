package com.jxust.we_chat_together.domain;

import java.util.List;

public class UserList {
    private int uid;
    private List<User> friendList;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "uid=" + uid +
                ", friendList=" + friendList +
                '}';
    }
}
