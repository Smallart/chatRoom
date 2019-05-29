package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

import java.util.List;
import java.util.Map;

public class FriendInfoPacket implements Packet {
    private Map<String, UserInfoMap> userInfo;

    public Map<String, UserInfoMap> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, UserInfoMap> userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.FRIENDS_INFO;
    }
}
