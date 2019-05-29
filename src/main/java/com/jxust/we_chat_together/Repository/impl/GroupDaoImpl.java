package com.jxust.we_chat_together.Repository.impl;

import com.jxust.we_chat_together.Repository.GroupDao;
import com.jxust.we_chat_together.domain.Group;
import com.jxust.we_chat_together.domain.Role;
import com.jxust.we_chat_together.domain.UserGourpRole;
import com.jxust.we_chat_together.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao
{
    @Autowired
    GroupMapper groupMapper;
    @Override
    public List<Role> getAllRole() {
        return groupMapper.getAllRole();
    }

    @Override
    public Group checkGroupName(String groupName) {
        return groupMapper.checkGroupName(groupName);
    }

    @Override
    public int addGroup(Group group) {
        return groupMapper.addGroup(group);
    }

    @Override
    public int insertUserAndGroup(UserGourpRole userGourpRole) {
        return groupMapper.insertUserAndGroup(userGourpRole);
    }

    @Override
    public int getMaxIdFromGroup() {
        return groupMapper.getMaxIdFromGroup();
    }

    @Override
    public Group getAllGroups(int gid) {
        return groupMapper.getAllGroups(gid);
    }

    @Override
    public List<Group> searchGroups(String content) {
        return groupMapper.searchGroups(content);
    }

    @Override
    public Group findGroupById(int gid) {
        return groupMapper.findGroupById(gid);
    }

    @Override
    public int exitGourp(int uid, int gid) {
        return groupMapper.exitGourp(uid,gid);
    }

    @Override
    public int getInfoMaxCount() {
        return groupMapper.getInfoMaxCount();
    }
}
