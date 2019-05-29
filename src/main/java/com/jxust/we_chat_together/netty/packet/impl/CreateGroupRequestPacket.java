package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class CreateGroupRequestPacket implements Packet {
    private String groupUUID;
    private String userUUID;

    public String getGroupUUID() {

        return groupUUID;
    }

    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.CREATE_GROUP_REQUEST;
    }
}
