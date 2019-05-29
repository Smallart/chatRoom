package com.jxust.we_chat_together.Repository;

import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.domain.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    User checkUserByName(String name);
    int registerUser(User user);
    User userLogin(User user);
    int updateUserState(User user);
    UserList friendsList(int id);
    List<UserInfoMap> userInfoMap(int id);
    User LoginOut(String uid);
    int deleteFriendById(int uId,int friendId);
    List<User> searchUsers(String content);
    User getUserGroup(int id);//通过Id获得当前对象的群组
    int userAddFriend(int uid,int fid);
    User findUserById(int uid);//通过ID查询用户
    int addGroup(int uid,int gind,int rid);//添加群组
    //添加用户信息
    int addFriendInfos(List<UserInfo> userInfos);
    //添加用户与信息之间的关系
    int addFriendWithInfo(int uid,int infoId,int fromId);
    //修改以读信息状态
    int changInfosState(UserInfo userInfo);
}
