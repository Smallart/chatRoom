package com.jxust.we_chat_together.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxust.we_chat_together.netty.packet.Packet;

import java.io.IOException;

/**
 * 工具类;
 * 用于将Packet进行Json化，或将Json字符串变为相应的Packet对象
 */
public class JsonUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();
    public static String codec(Packet packet) throws JsonProcessingException {
        return objectMapper.writeValueAsString(packet);
    }
    public static Packet deCode(String json) throws IOException {
        return objectMapper.readValue(json,Packet.class);
    }
}
