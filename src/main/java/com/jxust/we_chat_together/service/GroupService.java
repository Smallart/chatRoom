package com.jxust.we_chat_together.service;

import com.jxust.we_chat_together.domain.Group;
import com.jxust.we_chat_together.domain.Role;
import com.jxust.we_chat_together.domain.UserGourpRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GroupService {
    //获取所有的
    List<Role> getAllRole();
    Boolean checkGroupName(String groupName);
    //添加群组
    boolean addGroup(Group group);
    //插入到群组成员关联表中
    boolean insertUserAndGroup(UserGourpRole userGourpRole);
    //获取群组中最大值
    int getMaxIdFromGroup();
    //获得群组,当服务器启动时加载
    Group getAllGroups(int gid);
    //查询群主信息
    Group getGroupInfo(String groupName);
    //搜素群组信息
    List<Group> searchGroups(String content);
    //通过群组ID获取群组信息
    Group findGroupById(int gid);
    Boolean exitGourp(@Param("uId") int uid, @Param("gId") int gid); //退出群组
    int getInfoMaxCount();//获取数据庫中信息表的最大count值
}
