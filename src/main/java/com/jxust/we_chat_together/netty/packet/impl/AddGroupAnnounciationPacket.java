package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class AddGroupAnnounciationPacket implements Packet {
    private String userName;
    private String userImg;
    private String groupName;
    private int gid;

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.ADD_GROUP_ANNOUNCIATION;
    }
}
