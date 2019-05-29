package com.jxust.we_chat_together.netty.handler.impl;

import com.jxust.we_chat_together.netty.handler.IMHandler;
import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.impl.UserReadInfoPacket;
import com.jxust.we_chat_together.utils.TaskUtil;
import com.jxust.we_chat_together.utils.UserUtil;
import io.netty.channel.ChannelHandlerContext;

public class UserReadInfoHandler implements IMHandler {
    @Override
    public void operate(Packet packet, ChannelHandlerContext ctx) {
        UserReadInfoPacket userReadInfoPacket=(UserReadInfoPacket)packet;
        TaskUtil.getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                UserUtil.changeMessageState(userReadInfoPacket.getToId(),userReadInfoPacket.getFromId());
            }
        });
    }
}
