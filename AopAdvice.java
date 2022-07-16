package com.test.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AopAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("方法名称为："+method.getName());
        System.out.println("方法参数有："+ Arrays.toString(args));
        System.out.println("方法执行的对象为："+target);
    }
}
