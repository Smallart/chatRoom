package com.jxust.we_chat_together.utils;

import com.jxust.we_chat_together.domain.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工具类
 * 记录用户的好友列表
 * 以读数据和未读数据
 */
public class UserUtil {
    //好友列表
    private static final Map<String, List<User>> friendsMap=new ConcurrentHashMap<>();
    //信息提醒
    private static final Map<String,Map<String,UserInfoMap>> infoMap=new ConcurrentHashMap<>();
    //信息列表
    private static final Map<String,Map<String,UserInfoMap>> ReadedInfoMap=new ConcurrentHashMap<>();
    //群组列表
    private static final Map<String,List<Group>> groupList=new ConcurrentHashMap<>();

    public static Map<String, List<Group>> getGroupList() {
        return groupList;
    }

    public static Map<String, Map<String, UserInfoMap>> getReadedInfoMap() {
        return ReadedInfoMap;
    }

    public static Map<String, List<User>> getFriendsMap() {
        return friendsMap;
    }
    //添加用户
    public static void addFriend(UserList userList,String uId){
        ArrayList<User> user=new ArrayList<>();
        List<User> friendList = userList.getFriendList();
        if (friendList!=null){
            for (User u:friendList) {
                if (u.getUserState()==1){
                    user.add(u);
                }
            }
            friendsMap.put(uId,user);
        }
    }
    //删除用户
    public static void deleteFriend(String uid){
        friendsMap.remove(uid);
    }
    //获取用户消息
    public static Map<String, Map<String, UserInfoMap>> getInfoMap() {
        return infoMap;
    }
    //用户推送信息存储
    public static void unReadMessage(Map<String,Map<String,UserInfoMap>> infos,String uId,String fromId,UserInfoMap message){
        UserInfoMap userInfoMap = checkInfoExist(infos, uId, fromId);
        userInfoMap.setFromId(message.getFromId());
        userInfoMap.setInfoList(message.getInfoList());
    }

    //将对话信息存入
    public static void addOnlineMessage(Map<String,Map<String,UserInfoMap>> infos,String uId,String fromId,UserInfo userInfo,int id){
        UserInfoMap userInfoMap = checkInfoExist(infos,uId, fromId);
        userInfoMap.setFromId(id);
        List<UserInfo> infoList =userInfoMap.getInfoList();
        if (infoList==null){
            infoList=new ArrayList<>();
            userInfoMap.setInfoList(infoList);
        }
        infoList.add(userInfo);
    }
    public static UserInfoMap checkInfoExist(Map<String,Map<String,UserInfoMap>> infos,String uId,String fromId){
        Map<String, UserInfoMap> stringUserInfoMapMap = infos.get(uId);
        if (stringUserInfoMapMap==null){
            stringUserInfoMapMap=new HashMap<>();
            infos.put(uId,stringUserInfoMapMap);
        }
        UserInfoMap userInfoMap = stringUserInfoMapMap.get(fromId);
        if (userInfoMap==null){
            userInfoMap=new UserInfoMap();
            stringUserInfoMapMap.put(fromId,userInfoMap);
        }
        if (userInfoMap.getInfoList()==null){
            userInfoMap.setInfoList(new ArrayList<UserInfo>());
        }
        return userInfoMap;
    }
    //获取用户某一好友发送消息的数量
    public static int getCount(String uId,String fromId){
        Map<String, UserInfoMap> stringUserInfoMapMap = infoMap.get(uId);
        if (stringUserInfoMapMap==null) return 0;
        UserInfoMap userInfoMap = stringUserInfoMapMap.get(fromId);
        if (userInfoMap==null) return 0;
        List<UserInfo> infoList = userInfoMap.getInfoList();
        if (infoList==null) return 0;
        return infoList.size();
    }
    //交换将未读的消息变为已读
    public static void changeMessageState(String toUid,String fromUid){
        UserInfoMap userInfoMap = checkInfoExist(infoMap, toUid, fromUid);
        UserInfoMap readedUserInfoMap = checkInfoExist(ReadedInfoMap, toUid, fromUid);
        List<UserInfo> unReadInfoList = userInfoMap.getInfoList();
        List<UserInfo> readedInfoList = readedUserInfoMap.getInfoList();
        readedUserInfoMap.setFromId(userInfoMap.getFromId());
        if (unReadInfoList!=null&&readedInfoList!=null){
            for (UserInfo u:unReadInfoList){
                u.setState(State.READED);
            }
            readedInfoList.addAll(unReadInfoList);
            unReadInfoList.clear();
        }
    }
}
