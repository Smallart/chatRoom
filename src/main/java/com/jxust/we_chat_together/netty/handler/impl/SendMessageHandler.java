package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.ResponsePacket;
import com.jxust.we_chat_together.netty.packet.impl.ResponseToUserPacket;
import com.jxust.we_chat_together.netty.packet.impl.SendMessageToUserPacket;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import com.jxust.we_chat_together.utils.State;
import com.jxust.we_chat_together.utils.UserUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.*;

/*
* 用户发送消息，从channel中查找出消息接收方的channel
* 如果channel存在
*   信息接收方与你对话，则直接将消息发送到页面显示，并将数据放入该对象以读的信息集合中
*   信息接收发没有与你对话，则将数据放入未读信息集合中，并显示相应的条数
* channel不存在
*   将数据放入未读信息集合
* */
public class SendMessageHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext ctx) {
        SendMessageToUserPacket stp=(SendMessageToUserPacket)packet;
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        String message=stp.getMessage();//发送的消息
        String toUUID=stp.getToId();//消息发送给某人
        String formUUID=stp.getFormId();//消息发出的对象
        int id = stp.getId();
        //将发送的消息存储起来,交换UUID位置，防止脏读
        UserUtil.addOnlineMessage(UserUtil.getInfoMap(),formUUID,toUUID,saveInfo(message),id);
        //发送消息
        Channel ch = userIdChannel.get(toUUID);
        if (ch!=null){
            ResponseToUserPacket rtp = new ResponseToUserPacket();
            rtp.setFromId(formUUID);
            rtp.setMessage(message);
            try {
                ch.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(rtp)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
    public UserInfo saveInfo(String message){
        UserInfo userInfo = new UserInfo();
        userInfo.setMessage(message);
        userInfo.setState(State.UNREAD);
        userInfo.setInfotime(new Date());
        return userInfo;
    }
}