package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.InvitedPeopleToGourpPacket;
import com.jxust.we_chat_together.netty.packet.impl.InvitedPeopleToGroupResponsePacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class InvitedPeopleToGroupHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt){
        InvitedPeopleToGourpPacket invitedPeopleToGourpPacket=(InvitedPeopleToGourpPacket)packet;
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        Channel channel = userIdChannel.get(invitedPeopleToGourpPacket.getToUUID());
        InvitedPeopleToGroupResponsePacket invitedPeopleToGroupResponsePacket = new InvitedPeopleToGroupResponsePacket();
        invitedPeopleToGroupResponsePacket.setSuccess(false);
        //防止该用户没上线，从而导致的空指针异常，如果用户没有上线呢？
        if (channel!=null){
            Map<String, ChannelGroup> channelGroup = SessionUtil.getChannelGroup();
            ChannelGroup channelGroups = channelGroup.get(invitedPeopleToGourpPacket.getGroupUUID());
            if (!channelGroups.contains(channel)){
                channelGroups.add(channel);
                try {
                    channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(invitedPeopleToGourpPacket)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                invitedPeopleToGroupResponsePacket.setSuccess(true);
            }
        }
        try {
            cxt.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(invitedPeopleToGroupResponsePacket)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
