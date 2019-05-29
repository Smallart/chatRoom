package com.jxust.we_chat_together.netty.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.packet.Packet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public interface IMHandler{
    void operate(Packet packet, ChannelHandlerContext cxt) throws JsonProcessingException;
}
