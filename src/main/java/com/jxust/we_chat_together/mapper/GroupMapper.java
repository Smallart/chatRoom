package com.jxust.we_chat_together.mapper;

import com.jxust.we_chat_together.domain.Group;
import com.jxust.we_chat_together.domain.Role;
import com.jxust.we_chat_together.domain.UserGourpRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface GroupMapper {
    //获取所有的角色权限
    List<Role> getAllRole();
    //检验群名是否重复
    Group checkGroupName(String groupName);
    //添加群组
    int addGroup(Group group);
    //插入到群组成员关联表中
    int insertUserAndGroup(UserGourpRole userGourpRole);
    //获得Group中最大的ID
    int getMaxIdFromGroup();
    //获得群组,当服务器启动时加载
    Group getAllGroups(int gid);
    //通过群组ID获取群组信息
    Group findGroupById(int gid);
    //搜素群组信息
    List<Group> searchGroups(String content);
    int exitGourp(@Param("uId") int uid, @Param("gId") int gid); //退出群组
    int getInfoMaxCount();//获取数据庫中信息表的最大count值

}
