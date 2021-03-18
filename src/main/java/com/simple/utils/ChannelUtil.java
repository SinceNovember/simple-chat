package com.simple.utils;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUtil {

    //定义一个Map结构存储userID映射到channel
    private static final Map<String, Channel> userIdChannel = new ConcurrentHashMap<>();

    public static void saveChannel(String userId, Channel channel) {
        userIdChannel.put(userId, channel);
    }

    //当用户退出后删除用户channel
    public static void removeChannel(String userId){
        if(!userIdChannel.containsKey(userId)){
                throw new RuntimeException("未找到当前用户的channel");
        }
        userIdChannel.remove(userId);
    }

    //通过userId查找channel
    public static Channel getChannel(String userId){
        return userIdChannel.get(userId);
    }

}
