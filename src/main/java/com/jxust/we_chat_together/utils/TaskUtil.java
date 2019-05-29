package com.jxust.we_chat_together.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskUtil {
    private static ExecutorService cachedThreadPool= Executors.newCachedThreadPool();

    private TaskUtil() {
    }

    public static ExecutorService getCachedThreadPool() {
        return cachedThreadPool;
    }
}
