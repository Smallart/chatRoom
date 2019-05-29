package com.jxust.we_chat_together.netty.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.Repository.GroupDao;
import com.jxust.we_chat_together.Repository.impl.GroupDaoImpl;
import com.jxust.we_chat_together.domain.Group;
import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.FriendInfoPacket;
import com.jxust.we_chat_together.netty.packet.impl.LoginRequestPacket;
import com.jxust.we_chat_together.netty.packet.impl.LoginResponsePakcet;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.SessionUtil;
import com.jxust.we_chat_together.utils.State;
import com.jxust.we_chat_together.utils.UserUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LoginHandller implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext ctx) {
        //获取登录请求包
        LoginRequestPacket loginPacket=(LoginRequestPacket)packet;
        String uuId = loginPacket.getUuId();
        SessionUtil.bindChannel(uuId,ctx.channel());
        //向登录的好友发送上线通知
        Map<String, List<User>> friendsMap = UserUtil.getFriendsMap();
        List<User> users = friendsMap.get(uuId);
        LoginResponsePakcet loginResponsePakcet = new LoginResponsePakcet(loginPacket.getUuId(), State.ALLIVE);
        //获取channel集合,发送消息
        Map<String, Channel> userIdChannel = SessionUtil.getUserIdChannel();
        for (User u:users) {
            Channel userChannel = userIdChannel.get(u.getUserId());
            if (userChannel!=null){
                try {
                    userChannel.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(loginResponsePakcet)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        //将群组初始化
        List<Group> allGroups = UserUtil.getGroupList().get(uuId);
        if (allGroups!=null){
            Map<String, ChannelGroup> channelGroups = SessionUtil.getChannelGroup();
            for (Group group:allGroups){
                String guuId = group.getGuuId();
                ChannelGroup groupChannel = channelGroups.get(guuId);
                if (groupChannel==null){
                    ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
                    SessionUtil.bindGroupChannel(guuId,channelGroup);
                    addChannel(channelGroup,group,userIdChannel);
                }else {
                    addChannel(groupChannel,group,userIdChannel);
                }

            }
        }
        //将未读信息发送到前端
        Map<String, Map<String, UserInfoMap>> infoMap = UserUtil.getInfoMap();
        Map<String, UserInfoMap> stringListMap = infoMap.get(uuId);
        FriendInfoPacket friendInfoPacket = new FriendInfoPacket();
        friendInfoPacket.setUserInfo(stringListMap);
        //清除其中消息
        try {
            ctx.writeAndFlush(new TextWebSocketFrame(JsonUtil.codec(friendInfoPacket)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    //将自己的channel放入群主channel中
    public void addChannel(ChannelGroup channelGroup,Group group,Map<String, Channel> userChannel){
        for (User user:group.getUsers()){
            Channel channel = userChannel.get(user.getUserId());
            if (channelGroup!=null){
                if (channel!=null&&!channelGroup.contains(channel)){
                    channelGroup.add(channel);
                }
            }

        }
    }
}
