package com.jxust.we_chat_together.utils;

import java.util.UUID;

/**
 * 给创建的用户或是群组一个UUID标识
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
