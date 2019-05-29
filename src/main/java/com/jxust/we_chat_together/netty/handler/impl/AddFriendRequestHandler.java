package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.AddFriendRequestPacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class AddFriendRequestHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt){
        AddFriendRequestPacket addFriendRequestPacket=(AddFriendRequestPacket) packet;
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        Channel channel = userIdChannel.get(addFriendRequestPacket.getToUid());
        if (channel!=null){
            try {
                channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(addFriendRequestPacket)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }
}
