package com.yigiteren.starter.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.yigiteren.starter.services.StudentService.saveStudent(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable{
        log.info("Student is being registered");
        Object result = pjp.proceed();
        log.info("Student is registered.");
        return result;
    }
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void getLogging(){}

    @Around("getLogging()")
    public Object getLog(ProceedingJoinPoint pjp) throws Throwable{
        log.info("Get method invoked.");
        Object result = pjp.proceed();
        log.info("Get method invocation terminated.");
        return result;
    }
}
