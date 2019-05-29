package com.jxust.we_chat_together.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxust.we_chat_together.Repository.UserDao;
import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.domain.UserList;
import com.jxust.we_chat_together.service.UserService;
import com.jxust.we_chat_together.utils.ImgUpload;
import com.jxust.we_chat_together.utils.State;
import com.jxust.we_chat_together.utils.UUIDUtils;
import com.jxust.we_chat_together.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;
    @Override
    public Boolean checkUserByName(String name) {
        if (userDao.checkUserByName(name)!=null){
            return false;
        }
        return true;
    }

    @Override
    public Boolean registerUser(User user) {
        try {
            String headImg = ImgUpload.savaImage(user.getBlFile());
            user.setUserHeadImg(headImg);
            user.setUserId(UUIDUtils.getUUID());
            user.setUserState(State.UNLIVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userDao.registerUser(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkLoginUserName(String name) {
        if (userDao.checkUserByName(name)!=null){
            return true;
        }
        return false;
    }

    @Override
    public User userLogin(User user) {
        User u = userDao.userLogin(user);
        if (u!=null){
            u.setUserState(State.ALLIVE);
            updateUserState(u);
            return u;
        }
        return null;
    }

    @Override
    public int updateUserState(User user) {
        return userDao.updateUserState(user);
    }

    @Override
    public UserList friendsList(int id,String uId) {
        UserList userList = userDao.friendsList(id);
        //将在线的好友添加到Map中。
        UserUtil.addFriend(userList,uId);
        return userList;
    }

    @Override
    public List<UserInfoMap> userInfoMap(int id) {
        return userDao.userInfoMap(id);
    }

    //登出功能
    @Override
    public int LoginOut(String uid) {
        User user = userDao.LoginOut(uid);
        if (user!=null){
            user.setUserState(State.UNLIVE);
            return userDao.updateUserState(user);
        }
        return 0;
    }

    @Override
    public Boolean deleteFriendById(int uId, int friendId) {
        if (userDao.deleteFriendById(uId,friendId)>0){
            return true;
        }
        return false;
    }

    @Override
    public String searchUsers(String content) {
        ObjectMapper objectMapper=new ObjectMapper();
        List<User> users = userDao.searchUsers(content);
        if (users!=null){
            try {
                //返回JSON格式的user列表
                return objectMapper.writeValueAsString(users);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User getUserGroup(int id) {
        return userDao.getUserGroup(id);
    }

    @Override
    public Boolean userAddFriend(int uid, int fid) {
        if (userDao.userAddFriend(uid,fid)>0){
            return true;
        }
        return false;
    }

    @Override
    public User findUserById(int uid) {
        return userDao.findUserById(uid);
    }

    @Override
    public Boolean addGroup(int uid, int gind, int rid) {
        if (userDao.addGroup(uid, gind, rid)>1) return true;
        return false;
    }

    @Override
    public int addFriendInfos(List<UserInfo> userInfos) {
        return userDao.addFriendInfos(userInfos);
    }

    @Override
    public int addFriendWithInfo(int uid, int infoId, int fromId) {
        return userDao.addFriendWithInfo(uid,infoId,fromId);
    }

    @Override
    public int changInfosState(UserInfo userInfo) {
        return userDao.changInfosState(userInfo);
    }
}
