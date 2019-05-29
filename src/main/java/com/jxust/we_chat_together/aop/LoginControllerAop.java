package com.jxust.we_chat_together.aop;

import com.jxust.we_chat_together.controller.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginControllerAop {
    Logger logger=LogManager.getLogger(LoginController.class);
    @Before("execution(* com.jxust.we_chat_together.controller.LoginController.welCome()))")
    public void userWelcome(){
        logger.info("用户进入userWelcome()->跳转到登录界面");
    }
    @Before("execution(* com.jxust.we_chat_together.controller.LoginController.registerUser(..)))")
    public void registerUser(){logger.info("用户进入registerUser()->进行注册逻辑"); }
    @Before("execution(* com.jxust.we_chat_together.controller.LoginController.userLogin(..)))")
    public void userLogin(){logger.info("用户进入userLogin()->进行登录逻辑"); }
    @Before("execution(* com.jxust.we_chat_together.controller.LoginController.checkUserByName(..)))")
    public void checkUserByName(){logger.info("用户进入checkUserByName()->注册时检验用户名是否重复"); }
    @Before("execution(* com.jxust.we_chat_together.controller.LoginController.checkLoginName(..)))")
    public void checkLoginName(){logger.info("用户进入checkLoginName()->登录检验输入用户名是否正确");}
    @Before("execution(* com.jxust.we_chat_together.controller.LoginController.outLogin(..)))")
    public void outLogin(){logger.info("用户进入outLogin()->用户退出");}

}
