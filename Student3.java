package com.test.bean;

import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@ToString

//@Component声明Bean
@Component
public class Student3 {
    int age;
    String name;

    //@Resource自动注入 默认是ByName
    @Resource
    Card3 card;

    public String say(String text){
        System.out.println("我叫"+name+"今年"+age+"岁");
        return text;
    }
}
