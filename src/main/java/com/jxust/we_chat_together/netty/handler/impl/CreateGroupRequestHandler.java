package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.CreateGroupRequestPacket;
import com.jxust.we_chat_together.netty.packet.impl.ResponsePacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class CreateGroupRequestHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt) throws JsonProcessingException {
        CreateGroupRequestPacket cgrp=(CreateGroupRequestPacket)packet;
        DefaultChannelGroup channels = new DefaultChannelGroup(cxt.executor());
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        Channel channel = userIdChannel.get(cgrp.getUserUUID());
        channels.add(channel);
        Map<String, ChannelGroup> channelGroup = SessionUtil.getChannelGroup();
        channelGroup.put(cgrp.getGroupUUID(),channels);
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setSuccess(true);
        cxt.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(responsePacket)));
    }
}
