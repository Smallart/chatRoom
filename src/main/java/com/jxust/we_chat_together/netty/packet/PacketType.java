package com.jxust.we_chat_together.netty.packet;

public interface PacketType {
    //用户登录请求
    Byte USER_LOGIN=1;
    //用户发出上线通知
    Byte USER_ONLINE=2;
    //通用回复信息
    Byte RESPONSE=3;
    //私聊发送消息
    Byte SEND_MESSAGE_TO_USER=4;
    //私聊回复信息
    Byte RESPONSE_MESSAGE_TO_USER=5;
    //初始化登陆后信息
    Byte FRIENDS_INFO=6;
    //用户读完好友信息
    Byte USER_READED=7;
    //一人在群组发消息
    Byte GROUP_MESSAGE_REQUEST=8;
    //添加好友
    Byte ADD_FRIEND_REQUEST=9;
    //推荐好友进入群组
    Byte INVITED_FRIEND_TO_GROUP=10;
    //推荐好友进入群组回复
    Byte INVITED_REIEND_TO_GROUP_RESPONSE=12;
    //通知其他人
    Byte ANNUNCIATION_GROUP_OTHERS=11;
    //用户的下线通知
    Byte LOGINOUT_REQUEST=13;
    //退群请求
    Byte EXIT_GROUP_REQUEST=14;
    //退群声明
    Byte EXIT_GROUP_RESPONSE=15;
    //加群请求
    Byte ADD_GROUP_REQUEST=16;
    //加群声明
    Byte ADD_GROUP_ANNOUNCIATION=17;
    //创建群请求
    Byte CREATE_GROUP_REQUEST=18;
}
