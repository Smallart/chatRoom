package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class LoginOutRequestPakcet implements Packet {
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.LOGINOUT_REQUEST;
    }
}
