package com.jxust.we_chat_together.utils;

import com.jxust.we_chat_together.domain.GroupInfo;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工具类：
 * 存储用户的UID与Channel
 * 存储群组的GID与Channel
 */
public class SessionUtil {
    private static final Map<String, Channel> userIdChannel=new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> channelGroup=new ConcurrentHashMap<>();
    private static final Map<String, List<GroupInfo>> groupInformation=new ConcurrentHashMap<>();

    public static Map<String, Channel> getUserIdChannel() {
        return userIdChannel;
    }
    public static Map<String, ChannelGroup> getChannelGroup() {
        return channelGroup;
    }
    public static Map<String, List<GroupInfo>> getGroupInformation() {
        return groupInformation;
    }

    public static void bindChannel(String uuId, Channel channel){
        userIdChannel.put(uuId,channel);
    }
    public static void unBindChannel(String uuId){
        userIdChannel.remove(uuId);
    }
    public static void bindGroupChannel(String uuIdGroup, ChannelGroup groupChannel){
        channelGroup.put(uuIdGroup,groupChannel);
    }

    public static void unbindGroupChannel(String uuIdGroup){
        channelGroup.remove(uuIdGroup);
    }
    public static void saveGroupInfo(String guid,GroupInfo groupInfo){
        List<GroupInfo> groupInfos = groupInformation.get(guid);
        if (groupInfos==null){
            List<GroupInfo> infoList=new ArrayList<>();
            infoList.add(groupInfo);
            groupInformation.put(guid,infoList);
        }
        groupInfos.add(groupInfo);

    }
    public static boolean existGroup(String guid){
        if (groupInformation.get(guid)!=null){
            return true;
        }
        return false;
    }
}
