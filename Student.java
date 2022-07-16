package com.test.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Student {
    String name;
    int age;
    List<String> list;
    Map<String,Double> score;

    public Student(){
        System.out.println("我被构造了");
    }

    public void init(){
        System.out.println("我是初始化方法");
    }

    public void destroy(){
        System.out.println("我是销毁方法");
    }
}
