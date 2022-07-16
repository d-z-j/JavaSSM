package com.test.config;

import com.test.bean.Card;
import com.test.bean.Student1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//这个类替代了之前的xml配置文件
@Configuration
public class MainConfiguration {
    //声明Bean
    @Bean
    //@Scope("prototype")  //@Scope指定作用域
    public Card card(){
        return new Card();
    }
    @Bean
    public Student1 student1(){
        Student1 student1 = new Student1();
        student1.setName("小明");
        return student1;
    }
}
