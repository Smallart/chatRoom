package com.jxust.we_chat_together.service;

import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.domain.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //通过姓名检查用户是否存在
    Boolean checkUserByName(String name);
    //注册用户
    Boolean registerUser(User user);
    //检测登录用否存在
    Boolean checkLoginUserName(String name);
    //用户登录
    User userLogin(User user);
    //改变用户的状态
    int updateUserState(User user);
    //获取好友列表
    UserList friendsList(int id,String uId);
    //获取消息列表
    List<UserInfoMap> userInfoMap(int id);
    //用户登出操作
    int LoginOut(String uid);
    //通过id删除好友
    Boolean deleteFriendById(int uId,int friendId);
    //通过内容模糊查询用户
    String searchUsers(String content);
    //通过Id获得当前对象的群组
    User getUserGroup(int id);
    //添加好友
    Boolean userAddFriend(int uid,int fid);
    User findUserById(int uid);//通过ID查询用户
    Boolean addGroup(int uid,int gind,int rid);//添加群组
    //添加用户信息
    int addFriendInfos(List<UserInfo> userInfos);
    //添加用户与信息之间的关系
    int addFriendWithInfo(int uid,int infoId,int fromId);
    //修改以读信息状态
    int changInfosState(UserInfo userInfo);
}
