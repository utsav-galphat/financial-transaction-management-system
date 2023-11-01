package com.ut.useraccountservice.config;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution( * com.ut.useraccountservice.controller.*.*(..) )")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void logBeforeControllerMethod(JoinPoint joinPoint){
        String endpoint = joinPoint.toShortString();
        logger.info("Entering endpoint: [" + endpoint  + "]");
    }

    @AfterReturning("controllerMethods()")
    public void logAfterControllerMethod(JoinPoint joinPoint) {
        String endpoint = joinPoint.toShortString();
        logger.info("Exiting endpoint: [" + endpoint +  "]");
    }
}
