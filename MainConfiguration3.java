package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//自动扫描某一包下的Bean
@ComponentScan("com.test.bean")

@Configuration

//开启AOP操作
@EnableAspectJAutoProxy
public class MainConfiguration3 {

}
