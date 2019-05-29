package com.jxust.we_chat_together.mapper;

import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.domain.UserList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    List<User> getUser(); //获取所有User对象
    User checkUserByName(String name);//检验该用户名是否使用
    int registerUser(User user);//注册用户
    User userLogin(User user);//用户登录检查
    int updateUserState(User user); //修改用户状态
    UserList getFriendsList(int uid);//获取好友列表
    List<UserInfoMap> getUserInfo(int toId);//获取好友发送给用户的信息
    User LoginOut(String uid);//用户退出
    int deleteFriendByUserId(@Param("uId") int uid,@Param("friendId") int friendId);
    List<User> searchUsers(String content);//通过搜索内容搜索用户
    User getUserGroup(int id);//通过Id获得当前对象的群组
    int userAddFriend(@Param("uId") int uid,@Param("fId") int fid);//添加好友
    User findUserById(int uid);//通过ID查询用户
    //添加群组
    int addGroup(@Param("uId") int uid,@Param("gId") int gind,@Param("rId") int rid);
    //添加用户信息
    int addFriendInfos(@Param("userInfos") List<UserInfo> userInfos);
    //添加用户与信息之间的关系
    int addFriendWithInfo(@Param("uid") int uid,@Param("infoId") int infoId,@Param("fromId") int fromId);
    //修改以读信息状态
    int changInfosState(UserInfo userInfo);
}
