package com.jxust.we_chat_together.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ServerStart {
    @Autowired
    private ServerBootstrap serverBootstrap;

    private Channel serverChannel;

    public void start(){
        try {
            serverChannel= serverBootstrap.bind(8888).sync().channel().closeFuture().sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @PreDestroy
    public void stop(){
        serverChannel.close();
        serverChannel.parent().close();
    }
    @PostConstruct
    public void initial(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }).start();
    }
}
