package com.test;

import com.test.bean.Student1;
import com.test.config.MainConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Student1 student1 = context.getBean(Student1.class);
        System.out.println(student1);
    }
}
