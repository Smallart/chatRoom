package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class LoginResponsePakcet implements Packet {
    private String uuId;
    private int state;

    public LoginResponsePakcet() {
    }

    public LoginResponsePakcet(String uuId, int state) {
        this.uuId = uuId;
        this.state = state;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.USER_ONLINE;
    }
}
