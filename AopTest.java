package com.test.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

@Log
public class AopTest {
    public void after(JoinPoint point){
        //获取切入点方法的参数
        System.out.println(Arrays.toString(point.getArgs()));
        log.info("我是方法执行之后的日志");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("我是前置");
        joinPoint.proceed();
        System.out.println("我是环绕方法");
    }
}
