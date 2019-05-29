package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class SendMessageToUserPacket implements Packet {
    private String formId;
    private int id;
    private String toId;
    private String message;

    public String getFormId() {
        return formId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.SEND_MESSAGE_TO_USER;
    }
}
