package com.jxust.we_chat_together;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jxust.we_chat_together.config.MyConfig;
import com.jxust.we_chat_together.domain.*;
import com.jxust.we_chat_together.mapper.GroupMapper;
import com.jxust.we_chat_together.mapper.UserMapper;
import com.jxust.we_chat_together.netty.packet.impl.SendMessageToUserPacket;
import com.jxust.we_chat_together.service.UserService;
import com.jxust.we_chat_together.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatTogetherApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    GroupMapper groupMapper;

    @Autowired
    UserService userService;

    @Test
    public void getUserGroup(){
        User userGroup = userMapper.getUserGroup(31);
        System.out.println(userGroup);
    }
    @Test
    public void contextLoads() {
        List<User> user = userMapper.getUser();
        System.out.println(user);
    }
    @Test
    public void registerUser(){

        UserList friendsList = userMapper.getFriendsList(31);
        for (User us:friendsList.getFriendList()) {
            System.out.println(us);
        }
    }
    @Test
    public void getInfo(){
        List<UserInfoMap> userInfo = userMapper.getUserInfo(31);
        System.out.println(userInfo);
    }
    @Test
    public void getUserInfo(){
        List<UserInfoMap> userInfoMaps = userService.userInfoMap(31);
        for (UserInfoMap userInfoMap:userInfoMaps) {
            List<UserInfo> infoList = userInfoMap.getInfoList();
            for (UserInfo userInfo:infoList) {
                System.out.println(userInfo);
            }
        }
    }
    @Test
    public void getAllRole(){
        List<Role> allRole = groupMapper.getAllRole();
        System.out.println(allRole);
    }
    @Test
    public void getGroups(){
        Group allGroups = groupMapper.getAllGroups(3);
        System.out.println(allGroups);
    }
    @Test
    public void testFindUserbyId(){
        User userById = userMapper.findUserById(31);
        System.out.println(userById);
    }
    @Test
    public void getAllGroups(){
        Group groups = groupMapper.getAllGroups(3);
        System.out.println(groups.getUsers());
    }
    @Test
    public void getGroupById(){
        Group groupById = groupMapper.findGroupById(3);
        System.out.println(groupById);
    }
    @Test
    public void addGroup(){
        System.out.println(userMapper.addGroup(36,1,2));
    }
    @Test
    public void findAllGroup(){
        List<Group> groups = groupMapper.searchGroups("车");
        for (Group g:groups){
            System.out.println(g);
        }
    }
    @Test
    public void exitGroup(){
        int i = groupMapper.exitGourp(31, 18);
        System.out.println(i);
    }
    @Test
    public void saveInfos(){
        List<UserInfo> userInfos=new ArrayList<>();
        UserInfo one = new UserInfo();
        one.setState(1);
        one.setMessage("test");
        one.setInfotime(new Date());
        UserInfo two = new UserInfo();
        two.setState(1);
        two.setMessage("testTwo");
        two.setInfotime(new Date());
        userInfos.add(one);
        userInfos.add(two);
        int i = userMapper.addFriendInfos(userInfos);
        System.out.println(i);
    }
}
