package com.jxust.we_chat_together.netty.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.netty.handler.impl.*;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

public class HandlerManager {
    private Map<Byte, IMHandler> handlerMap=new HashMap<>();
    public HandlerManager(){
        handlerMap.put(PacketType.USER_LOGIN,new LoginHandller());
        handlerMap.put(PacketType.SEND_MESSAGE_TO_USER,new SendMessageHandler());
        handlerMap.put(PacketType.USER_READED,new UserReadInfoHandler());
        handlerMap.put(PacketType.GROUP_MESSAGE_REQUEST,new GroupMessageHandler());
        handlerMap.put(PacketType.ADD_FRIEND_REQUEST,new AddFriendRequestHandler());
        handlerMap.put(PacketType.INVITED_FRIEND_TO_GROUP,new InvitedPeopleToGroupHandler());
        handlerMap.put(PacketType.INVITED_REIEND_TO_GROUP_RESPONSE,new InvitedPeopleToGroupResponseHandler());
        handlerMap.put(PacketType.LOGINOUT_REQUEST,new LoginOutRequestHandler());
        handlerMap.put(PacketType.EXIT_GROUP_REQUEST,new ExitGroupRequestHandler());
        handlerMap.put(PacketType.ADD_GROUP_REQUEST,new AddGroupRequestHandler());
        handlerMap.put(PacketType.CREATE_GROUP_REQUEST,new CreateGroupRequestHandler());
    }
    public void operate(Packet packet, ChannelHandlerContext cxt){
        IMHandler imHandler = handlerMap.get(packet.getPacketType());
        try {
            imHandler.operate(packet,cxt);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
