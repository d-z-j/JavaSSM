package com.test;

import com.test.bean.Student2;
import com.test.config.MainConfiguration2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration2.class);
        Student2 student1 = context.getBean(Student2.class);
        System.out.println(student1);
    }
}
