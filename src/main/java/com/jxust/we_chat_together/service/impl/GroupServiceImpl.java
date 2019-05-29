package com.jxust.we_chat_together.service.impl;

import com.jxust.we_chat_together.Repository.GroupDao;
import com.jxust.we_chat_together.domain.Group;
import com.jxust.we_chat_together.domain.Role;
import com.jxust.we_chat_together.domain.UserGourpRole;
import com.jxust.we_chat_together.service.GroupService;
import com.jxust.we_chat_together.utils.ImgUpload;
import com.jxust.we_chat_together.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupDao groupDao;
    @Override
    public List<Role> getAllRole() {
        return groupDao.getAllRole();
    }

    @Override
    public Boolean checkGroupName(String groupName) {
        if (groupDao.checkGroupName(groupName)!=null)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean addGroup(Group group) {
        try {
            group.setGroupImg(ImgUpload.savaImage(group.getBlFile()));
            group.setGuuId(UUIDUtils.getUUID());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (groupDao.addGroup(group)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertUserAndGroup(UserGourpRole userGourpRole) {
        if (groupDao.insertUserAndGroup(userGourpRole)>0) return true;
        return false;
    }

    @Override
    public int getMaxIdFromGroup() {
        return groupDao.getMaxIdFromGroup();
    }

    @Override
    public Group getAllGroups(int gid) {
        return groupDao.getAllGroups(gid);
    }

    @Override
    public Group getGroupInfo(String groupName) {
        return groupDao.checkGroupName(groupName);
    }

    @Override
    public List<Group> searchGroups(String content) {
        return groupDao.searchGroups(content);
    }

    @Override
    public Group findGroupById(int gid) {
        return groupDao.findGroupById(gid);
    }

    @Override
    public Boolean exitGourp(int uid, int gid) {
        if (groupDao.exitGourp(uid,gid)>0) return true;
        return false;
    }

    @Override
    public int getInfoMaxCount() {
        return groupDao.getInfoMaxCount();
    }
}
