package com.jxust.we_chat_together.aop;

import com.jxust.we_chat_together.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserServerAop {
    Logger logger= LogManager.getLogger(UserService.class);
}
