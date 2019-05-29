package com.jxust.we_chat_together.netty.server;

import com.jxust.we_chat_together.netty.handler.MessageTransmission;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Server {
    @Bean
    public NioEventLoopGroup boosGroop(){
        return new NioEventLoopGroup();
    }
    @Bean
    public NioEventLoopGroup workerGroop(){
        return new NioEventLoopGroup();
    }
    @Bean
    public ServerBootstrap bootstrap(NioEventLoopGroup boosGroop,NioEventLoopGroup workerGroop){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroop,workerGroop)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new HttpServerCodec())
                                .addLast(new ChunkedWriteHandler())
                                .addLast(new HttpObjectAggregator(65536))
                                .addLast(new WebSocketServerProtocolHandler("/websocket"))
                                .addLast(new MessageTransmission());
                    }
                });
        return bootstrap;
    }
}
