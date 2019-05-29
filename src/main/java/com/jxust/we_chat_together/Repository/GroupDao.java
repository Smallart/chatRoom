package com.jxust.we_chat_together.Repository;

import com.jxust.we_chat_together.domain.Group;
import com.jxust.we_chat_together.domain.Role;
import com.jxust.we_chat_together.domain.UserGourpRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupDao {
    List<Role> getAllRole();
    Group checkGroupName(String groupName);
    //添加群组
    int addGroup(Group group);
    //插入到群组成员关联表中
    int insertUserAndGroup(UserGourpRole userGourpRole);
    //获取表中最大的ID值
    int getMaxIdFromGroup();
    //获得群组,当服务器启动时加载
    Group getAllGroups(int gid);
    //搜素群组信息
    List<Group> searchGroups(String content);
    //通过群组ID获取群组信息
    Group findGroupById(int gid);
    int exitGourp(@Param("uId") int uid, @Param("gId") int gid); //退出群组
    int getInfoMaxCount();//获取数据庫中信息表的最大count值
}
