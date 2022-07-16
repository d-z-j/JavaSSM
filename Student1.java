package com.test.bean;

import lombok.ToString;

@ToString
public class Student1 {
    int age;
    String name;
    Card card;

    public void setName(String name) {
        this.name = name;
    }
}
