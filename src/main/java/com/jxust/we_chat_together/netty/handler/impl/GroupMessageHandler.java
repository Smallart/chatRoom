package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.GroupMessageRequestPacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class GroupMessageHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt) {
        GroupMessageRequestPacket groupMessageRequestPacket=(GroupMessageRequestPacket)packet;
        Map<String, ChannelGroup> channelGroup = SessionUtil.getChannelGroup();
        ChannelGroup cg = channelGroup.get(groupMessageRequestPacket.getGuId());
        Channel inComingChannel = cxt.channel();
        for (Channel channel:cg) {
            if (channel!=inComingChannel){
                try {
                    channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(groupMessageRequestPacket)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
