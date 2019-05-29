package com.jxust.we_chat_together.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxust.we_chat_together.domain.*;
import com.jxust.we_chat_together.service.GroupService;
import com.jxust.we_chat_together.service.UserService;
import com.jxust.we_chat_together.utils.JsonUtil;
import com.jxust.we_chat_together.utils.Role;
import com.jxust.we_chat_together.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ChatRoomController {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @RequestMapping("/Initial")
    public String InitialChatRoom(HttpServletRequest request){
        HttpSession session = request.getSession();
        //获取登录的user对象
        User user =(User)session.getAttribute("user");
        int uid=user.getId();
        String uuId=user.getUserId();
        //获取该登录user对象的好友列表
        UserList userLists = userService.friendsList(uid,uuId);
        //获取该登录对象的群组列表
        User userGroup = userService.getUserGroup(uid);
        //初始化群组channel
        Map<String, List<Group>> groupList = UserUtil.getGroupList();
        List<Group> groups=new ArrayList<>();
        for (Group g:userGroup.getGroups()){
            //获得相对应的群组中的群员
            Group groupWithUser = groupService.getAllGroups(g.getGid());
            //添加入集合中
            groups.add(groupWithUser);
        }
        groupList.put(uuId,groups);
        //获取登录的user对象的未读信息
        List<UserInfoMap> userInfoMaps = userService.userInfoMap(uid);
        List<User> friendList=userLists.getFriendList();
        //计算好友发送了多少条信息
        for (User u:friendList) {
            int formid=u.getId();
            for (UserInfoMap userInfoMap:userInfoMaps){
                if (formid==userInfoMap.getFromId()){
                    //将数据库中未读的数据放入相应的Map中
                   UserUtil.unReadMessage(UserUtil.getInfoMap(),uuId,u.getUserId(),userInfoMap);
                }
            }
            //获取信息数量
            u.setSendInformationNum(UserUtil.getCount(uuId,u.getUserId()));
        }
        //将群组信息发送到前端
        session.setAttribute("friendList",friendList);
        if (groups!=null){
            session.setAttribute("groupList",groups);
        }
        return "ChatRoom";
    }
    //删除好友
    @GetMapping("/deleteFriendByUser")
    @ResponseBody
    public Boolean deleteFriendByUser(@RequestParam("friendId") int friendId,
                                     @RequestParam("uId") int uid){
        userService.deleteFriendById(friendId,uid);
        return userService.deleteFriendById(uid,friendId);
    }
    //查询用户
    @PostMapping("/searchUser")
    @ResponseBody
    public String searchUser(@RequestParam("content") String content){
        return userService.searchUsers(content);
    }
    //检测群名是否重复
    @GetMapping("/checkGroupName")
    @ResponseBody
    public String checkGroupName(@RequestParam("createGroupName") String groupName){
        String s;
        if (groupName==null){
            s="{\"valid\":"+false+"}";
        }else{
            Boolean extis = groupService.checkGroupName(groupName);
            s="{\"valid\":"+extis+"}";
        }
        return s;
    }
    //添加群
    @PostMapping("/addGroup")
    @ResponseBody
    public String addGroup(Group group){
        groupService.addGroup(group);
        UserGourpRole userGourpRole =new UserGourpRole();
        userGourpRole.setUserId(group.getGid());
        userGourpRole.setGroupId(groupService.getMaxIdFromGroup());
        userGourpRole.setRoleId(Role.GROUP_CREATER);
        groupService.insertUserAndGroup(userGourpRole);

        Group groupById = groupService.findGroupById(groupService.getMaxIdFromGroup());
        ObjectMapper objectMapper = new ObjectMapper();
        String s=null;
        try {
            s=objectMapper.writeValueAsString(groupById);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
    //添加好友

    /**
     * 模糊查询会将所有信息查出故添加好友有以下几种情况
     * 1. uid=fid 自己添加自己 这个要进行判断
     * 2. uid！=fid 虽然自己没有添加自己，但是还是有问题，即fid不能是自己好友列表中的。
     * @param uid 用户ID
     * @param fid 添加好友的ID
     * @return
     */
    @PostMapping("/addFriend")
    @ResponseBody
    public String addFriend(@RequestParam("uId") int uid,@RequestParam("uuId") String uuId,
                             @RequestParam("fId") int fid){
        if (uid==fid) return "{\"message\":\"皮这一下很开心吗？不能添加自己\",\"flag\":false}";
        if(uid!=fid){
            Map<String, List<User>> friendsMap = UserUtil.getFriendsMap();
            if (friendsMap!=null) {
                List<User> users = friendsMap.get(uuId);
                User user = userService.findUserById(fid);
                if (users.contains(user)){
                    return "{\"message\":\"这已经是您好友了哦！\",\"flag\":false}";
                }
            }
        }
        userService.userAddFriend(fid,uid);
        userService.userAddFriend(uid,fid);
        return "{\"message\":\"添加成功\",\"flag\":true}";
    }
    @GetMapping("/findGroupUser")
    @ResponseBody
    public String findGroupUsers(@RequestParam("gid") int gid){
        ObjectMapper objectMapper = new ObjectMapper();
        Group groups = groupService.getAllGroups(gid);
        String s=null;
        try {
            s= objectMapper.writeValueAsString(groups.getUsers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
    @GetMapping("/getGroupInfo")
    @ResponseBody
    public String getGroupInfo(@RequestParam("groupName") String groupName){
        Group groupInfo = groupService.getGroupInfo(groupName);
        ObjectMapper objectMapper = new ObjectMapper();
        String s=null;
        try {
           s = objectMapper.writeValueAsString(groupInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
    @PostMapping("/addGroups")
    @ResponseBody
    public String addGroups(@RequestParam("uId") int uid,@RequestParam("gId") int gid,@RequestParam("uuId") String userId){
        Group groupById = groupService.findGroupById(gid);
        Map<String, List<Group>> groupList = UserUtil.getGroupList();
        List<Group> groups = groupList.get(userId);
        if (groups!=null){
            if (groups.contains(groupById)){
                //返回相应消息
                return "{\"message\":\"你已经在群中了哦！\",\"flag\":false}";
            }else {
                //实例化对象
                groups.add(groupById);
                UserGourpRole userGourpRole = new UserGourpRole();
                userGourpRole.setRoleId(Role.PEOPLE);
                userGourpRole.setGroupId(gid);
                userGourpRole.setUserId(uid);
                groupService.insertUserAndGroup(userGourpRole);
                return "{\"message\":\"添加成功\",\"flag\":true}";

            }
        }
        return null;
    }
    @PostMapping("/searchGroup")
    @ResponseBody
    public String searchGroup(@RequestParam("content") String seachValue){
        ObjectMapper objectMapper = new ObjectMapper();
        String s=null;
        List<Group> groups = groupService.searchGroups(seachValue);
        if (groups!=null) {
            try {
                s=objectMapper.writeValueAsString(groups);
                return s;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @PostMapping("/exitGroup")
    @ResponseBody
    public String exitGroup(@RequestParam("uId") int uId,@RequestParam("gId") int gId){
        if(groupService.exitGourp(uId, gId)){
            return "{\"message\":\"添加成功\",\"flag\":true}";
        }
        return "{\"message\":\"添加成功\",\"flag\":false}";
    }
}
