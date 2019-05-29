package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.AddGroupAnnounciationPacket;
import com.jxust.we_chat_together.netty.packet.impl.AddGroupRequestPacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import com.jxust.we_chat_together.utils.UserUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class AddGroupRequestHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt) throws JsonProcessingException {
       AddGroupRequestPacket agrp =(AddGroupRequestPacket)packet;
        Map<String, ChannelGroup> channelGroup = SessionUtil.getChannelGroup();
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        Channel channel = userIdChannel.get(agrp.getuId());
        if (channelGroup!=null){
            ChannelGroup channels = channelGroup.get(agrp.getGuId());
            if(channels==null){
                channels= new DefaultChannelGroup(cxt.executor());
                SessionUtil.bindGroupChannel(agrp.getGuId(),channels);
            }
            AddGroupAnnounciationPacket agap = new AddGroupAnnounciationPacket();
            agap.setGid(agrp.getGid());
            agap.setUserName(agrp.getUserName());
            agap.setUserImg(agrp.getUserImg());
            agap.setGroupName(agrp.getGroupName());
            channels.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(agap)));
            channels.add(channel);
        }
    }
}
