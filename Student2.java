package com.test.bean;

import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@ToString

//@Component声明Bean
@Component
public class Student2 {
    int age;
    String name;

    //@Resource自动注入 默认是ByName
    @Resource
    Card2 card;

    //调用初始化方法
    @PostConstruct
    public void init(){
        System.out.println("属性card为："+card);
    }
}
