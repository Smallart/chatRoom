package com.jxust.we_chat_together.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;


public class User {
    private Integer id;
    private String userId;
    private String userName;
    private String userPassword;
    private String userPhoneNum;
    private String userGender;
    private String userHeadImg;
    private int sendInformationNum;
    private List<Group> groups;
    private Integer userState;
    private MultipartFile blFile;

    public User(String userId, String userName, String userPassword, String userPhoneNum, String userGender, String userHeadImg, Integer userState) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNum = userPhoneNum;
        this.userGender = userGender;
        this.userHeadImg = userHeadImg;
        this.userState = userState;
    }

    public User() {
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public MultipartFile getBlFile() {
        return blFile;
    }

    public void setBlFile(MultipartFile blFile) {
        this.blFile = blFile;
    }

    public int getSendInformationNum() {
        return sendInformationNum;
    }

    public void setSendInformationNum(int sendInformationNum) {
        this.sendInformationNum = sendInformationNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoneNum='" + userPhoneNum + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", sendInformationNum=" + sendInformationNum +
                ", groups=" + groups +
                ", userState=" + userState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
