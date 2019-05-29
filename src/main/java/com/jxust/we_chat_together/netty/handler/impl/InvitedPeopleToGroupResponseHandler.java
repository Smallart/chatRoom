package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.AnnounceOtherPeoplePakcet;
import com.jxust.we_chat_together.netty.packet.impl.InvitedPeopleToGroupResponsePacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class InvitedPeopleToGroupResponseHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt) throws JsonProcessingException {
        InvitedPeopleToGroupResponsePacket iptgrp=(InvitedPeopleToGroupResponsePacket)packet;
        Map<String, ChannelGroup> channelGroup = SessionUtil.getChannelGroup();
        ChannelGroup channels = channelGroup.get(iptgrp.getGuuid());
        Channel inComingChannel = cxt.channel();
        if (channels!=null){
            AnnounceOtherPeoplePakcet announceOtherPeoplePakcet = new AnnounceOtherPeoplePakcet();
            announceOtherPeoplePakcet.setGuid(iptgrp.getGid());
            announceOtherPeoplePakcet.setInviteName(iptgrp.getInviteName());
            announceOtherPeoplePakcet.setAccpetName(iptgrp.getAcceptName());
            announceOtherPeoplePakcet.setAccpetImg(iptgrp.getAccpetImg());
            announceOtherPeoplePakcet.setGroupName(iptgrp.getGroupName());
            for (Channel channel:channels){
                if (channel!=inComingChannel){
                    channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(announceOtherPeoplePakcet)));
                }
            }
        }
    }
}
