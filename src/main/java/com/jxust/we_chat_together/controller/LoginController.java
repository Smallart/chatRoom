package com.jxust.we_chat_together.controller;
import com.jxust.we_chat_together.domain.User;
import com.jxust.we_chat_together.domain.UserInfo;
import com.jxust.we_chat_together.domain.UserInfoMap;
import com.jxust.we_chat_together.service.GroupService;
import com.jxust.we_chat_together.service.UserService;
import com.jxust.we_chat_together.utils.SessionUtil;
import com.jxust.we_chat_together.utils.TaskUtil;
import com.jxust.we_chat_together.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    //进入登录界面
    /*
    * 一般用于跳转
    * */
    @RequestMapping(value = "/chatroom",method = RequestMethod.GET)
    public ModelAndView welCome(){
        return new ModelAndView("Login");
    }
    //用户登录
    /*
    * 用户进行登录，将用户名，密码传入处理
    * 如果用户名或密码为空，设置相应的提示信息，并返回到登录界面
    * 然后再进行登录检验，看用户密码是否正确，如果正确就跳到聊天主页
    * 若失败，则设置相应提示信息，并返回到登入界面
    * */
    @RequestMapping(value = "/chatroom",method = RequestMethod.POST)
    public String userLogin(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("message")!=null){
            session.removeAttribute("message");
        }
        if(user.getUserName()==null||user.getUserPassword()==null){
            session.setAttribute("message","密码或用户名不能为空");
            return "/chatroom";
        }
        User u=userService.userLogin(user);
        if (u!=null){
            session.setAttribute("user",u);
            return "redirect:/Initial";
        }
        session.setAttribute("message","密码错误");
        return "redirect:/chatroom";
    }
    //注册时验证该姓名是否存在
    @ResponseBody
    @GetMapping("/checkUserByName")
    public String checkUserByName(@RequestParam("userName") String name){
        String s;
        if (name==null){
            s="{\"valid\":"+false+"}";
        }else{
            Boolean aBoolean = userService.checkUserByName(name);
            s="{\"valid\":"+aBoolean+"}";
        }
        return s;
    }
    //登录时检查数据库中是否存在该用户
    @ResponseBody
    @GetMapping("/checkLoginName")
    public String checkLoginName(@RequestParam("userName") String name){
        String s;
        if (name==null){
            s="{\"valid\":"+false+"}";
        }else{
            Boolean extis = userService.checkLoginUserName(name);
            s="{\"valid\":"+extis+"}";
        }
        return s;
    }
    //注册该用户
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public String registerUser(User user){
        userService.registerUser(user);
        return "redirect:/chatroom";
    }

    //退出清除记录
    @GetMapping("/outLogin")
    @ResponseBody
    public String outLogin(HttpServletRequest httpServletRequest,@RequestParam("uuid") String uid,@RequestParam("id") int id) {
        HttpSession session = httpServletRequest.getSession();
        httpServletRequest.getParameter("uuid");
        SessionUtil.unBindChannel(uid);
        session.invalidate();
        //解绑channel
        userService.LoginOut(uid);
        //清除好友Map中对应的一项
        UserUtil.getFriendsMap().get(uid).clear();
        //清除群组中对应的一项
        UserUtil.getGroupList().get(uid).clear();
        //获取数据库未读消息列表
        List<UserInfoMap> userInfoMaps = userService.userInfoMap(id);
        //获取程序运行中未读消息 infoMap用户上线后未读信息
        Map<String, Map<String, UserInfoMap>> infoMap = UserUtil.getInfoMap();
        //userInfos 用户未读信息
        Map<String, UserInfoMap> userInfos = infoMap.get(uid);

        //将没有动过的消息处理掉
        Set<String> keys = userInfos.keySet();
        for (String key:keys){
            UserInfoMap userInfoMap = userInfos.get(key);
            for (UserInfoMap userInfo:userInfoMaps){
                if (userInfo.getFromId()==userInfoMap.getFromId()){
                    Iterator<UserInfo> iterator = userInfoMap.getInfoList().iterator();
                    while(iterator.hasNext()){
                        if (iterator.next().getId()!=null) iterator.remove();
                    }
                }
            }
            saveTheMessage(id,userInfoMap);
        }

        //获取程序运行中已读信息
        Map<String, Map<String, UserInfoMap>> readedInfoMap = UserUtil.getReadedInfoMap();
        Map<String, UserInfoMap> userReadedInfo = readedInfoMap.get(uid);
        Set<String> readKeys = userReadedInfo.keySet();
        //将其存入到数据库中
        for (String key:readKeys){
            UserInfoMap userInfoMap = userReadedInfo.get(key);
            for (UserInfoMap userInfo:userInfoMaps){
                if (userInfo.getFromId()==userInfoMap.getFromId()){
                    Iterator<UserInfo> iterator = userInfoMap.getInfoList().iterator();
                    while(iterator.hasNext()){
                        UserInfo next = iterator.next();
                        if (next.getId()!=null){
                            userService.changInfosState(next);
                            iterator.remove();
                        }
                    }
                }
            }
            saveTheMessage(id,userInfoMap);
        }

        userInfos.clear();
        readedInfoMap.clear();

        return "/chatroom";
    }
    private void saveTheMessage(int id,UserInfoMap userInfoMap){
            List<UserInfo> infoList = userInfoMap.getInfoList();
            if (infoList!=null&&infoList.size()>0){
                userService.addFriendInfos(infoList);
                int endCount=groupService.getInfoMaxCount();
                int size = infoList.size();
                while(size-->0){
                    userService.addFriendWithInfo(id,endCount--,userInfoMap.getFromId());
                }
            }
    }

    @PreDestroy
    public void shutDown(){
        TaskUtil.getCachedThreadPool().shutdown();
    }
}
