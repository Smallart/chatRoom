package com.jxust.we_chat_together.netty.handler;


import com.jxust.we_chat_together.utils.JsonUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
@ChannelHandler.Sharable
public class MessageTransmission extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private HandlerManager handlerManager=new HandlerManager();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        handlerManager.operate(JsonUtil.deCode(textWebSocketFrame.text()),ctx);
    }
}
