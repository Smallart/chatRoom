package com.jxust.we_chat_together.Repository.impl;


import com.jxust.we_chat_together.Repository.UserDao;
import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.domain.UserList;
import com.jxust.we_chat_together.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUserByName(String name) {
        return userMapper.checkUserByName(name);
    }

    @Override
    public int registerUser(User user) {
        return userMapper.registerUser(user);
    }

    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);
    }

    @Override
    public int updateUserState(User user) {
        return userMapper.updateUserState(user);
    }

    @Override
    public UserList friendsList(int id) {
        return userMapper.getFriendsList(id);
    }

    @Override
    public List<UserInfoMap> userInfoMap(int id) {
        return userMapper.getUserInfo(id);
    }

    @Override
    public User LoginOut(String uid) {
        return userMapper.LoginOut(uid);
    }

    @Override
    public int deleteFriendById(int uId, int friendId) {
        return userMapper.deleteFriendByUserId(uId,friendId);
    }

    @Override
    public List<User> searchUsers(String content) {
        return userMapper.searchUsers(content);
    }

    @Override
    public User getUserGroup(int id) {
        return userMapper.getUserGroup(id);
    }

    @Override
    public int userAddFriend(int uid, int fid) {
        return userMapper.userAddFriend(uid,fid);
    }

    @Override
    public User findUserById(int uid) {
        return userMapper.findUserById(uid);
    }

    @Override
    public int addGroup(int uid, int gind, int rid) {
        return userMapper.addGroup(uid,gind,rid);
    }

    @Override
    public int addFriendInfos(List<UserInfo> userInfos) {
        System.out.println(userInfos);
        return userMapper.addFriendInfos(userInfos);
    }

    @Override
    public int addFriendWithInfo(int uid, int infoId, int fromId) {
        return userMapper.addFriendWithInfo(uid, infoId, fromId);
    }

    @Override
    public int changInfosState(UserInfo userInfos) {
        return userMapper.changInfosState(userInfos);
    }

}
