package com.jxust.we_chat_together.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

public class Group {
    //id
    private Integer gid;
    //群组的标识
    private String guuId;
    //群的名字
    private String groupName;
    //群头像
    private String groupImg;
    //上传的文件
    private MultipartFile blFile;
    //含有的成员数
    private List<User> users;
    //其中管理员数量
    private List<UserWithRole> userWithRoles;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGuuId() {
        return guuId;
    }

    public void setGuuId(String guuId) {
        this.guuId = guuId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public MultipartFile getBlFile() {
        return blFile;
    }

    public void setBlFile(MultipartFile blFile) {
        this.blFile = blFile;
    }

    public List<UserWithRole> getUserWithRoles() {
        return userWithRoles;
    }

    public void setUserWithRoles(List<UserWithRole> userWithRoles) {
        this.userWithRoles = userWithRoles;
    }

    @Override
    public String toString() {
        return "Group{" +
                "guuId='" + guuId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupImg='" + groupImg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(guuId, group.guuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guuId);
    }
}
