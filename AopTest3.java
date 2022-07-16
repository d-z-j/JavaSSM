package com.test.bean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component  //先声明bean 注意文件所在位置 添加包扫描@ComponentScan
@Aspect //aop注解
public class AopTest3 {

    /**@Before("execution(* com.test.bean.Student3.say(..))")
    public void before(){
        System.out.println("我是方法执行之前要做的事情");
    }

    @AfterReturning("execution(* com.test.bean.Student3.say(..))")
    public void after(){
        System.out.println("我是方法执行之后的结果");
    }**/

    @Around("execution(* com.test.bean.Student3.say(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object val = point.proceed();
        return val;
    }
}
