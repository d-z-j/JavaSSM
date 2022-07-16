package com.test;

import com.test.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //IoC容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        //通过getBean获取Student对象
        Student student = (Student) context.getBean("student");
        System.out.println(student);
        context.close();
    }
}
