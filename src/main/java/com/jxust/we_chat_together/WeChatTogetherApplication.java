package com.jxust.we_chat_together;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jxust.we_chat_together.mapper")
public class WeChatTogetherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatTogetherApplication.class, args);
    }

}
