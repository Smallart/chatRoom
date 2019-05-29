package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class ResponsePacket implements Packet {
    private boolean isSuccess;

    public ResponsePacket() {
    }

    public ResponsePacket(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.RESPONSE;
    }
}
