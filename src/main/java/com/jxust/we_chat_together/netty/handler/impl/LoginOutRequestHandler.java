package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.LoginOutRequestPakcet;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import com.jxust.we_chat_together.utils.UserUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;
import java.util.Map;

public class LoginOutRequestHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext cxt) throws JsonProcessingException {
       LoginOutRequestPakcet lrp=(LoginOutRequestPakcet)packet;
       String uid=lrp.getUid();
        Map<String, List<User>> friendsMap = UserUtil.getFriendsMap();
        if (friendsMap!=null){
            List<User> users = friendsMap.get(uid);
            Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
            for(User u:users){
                Channel channel = userIdChannel.get(u.getUserId());
                if (channel!=null){
                    channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(lrp)));
                }
            }
        }
    }
}
