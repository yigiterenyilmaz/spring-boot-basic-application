package com.yigiteren.starter.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* com.yigiteren.starter.services.StudentService.saveStudent(..))")
    public void log(){
        System.out.println("Student will be saved.");
    }
}
