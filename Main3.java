package com.test;

import com.test.bean.Student3;
import com.test.config.MainConfiguration3;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main3 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration3.class);
        Student3 student1 = context.getBean(Student3.class);
        System.out.println(student1.say("lbwnb"));
    }
}
