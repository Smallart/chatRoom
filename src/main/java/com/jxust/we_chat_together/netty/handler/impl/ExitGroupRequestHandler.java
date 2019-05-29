package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.ExitGroupAnnouncePacket;
import com.jxust.we_chat_together.netty.packet.impl.ExitGroupRequestPacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import com.jxust.we_chat_together.utils.UserUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class ExitGroupRequestHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt) throws JsonProcessingException {
       ExitGroupRequestPacket egrp=(ExitGroupRequestPacket)packet;
       //获取群组Channels
        Map<String, ChannelGroup> channelGroup = SessionUtil.getChannelGroup();
        ChannelGroup channels = channelGroup.get(egrp.getGroupUUID());
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        Channel channel = userIdChannel.get(egrp.getUserUUID());
        if (channels.contains(channel)){
            channels.remove(channel);
        }
        ExitGroupAnnouncePacket egap = new ExitGroupAnnouncePacket();
        egap.setGid(egrp.getGid());
        egap.setGroupName(egrp.getGroupName());
        egap.setUserName(egrp.getUserName());
        channels.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(egap)));

    }
}
