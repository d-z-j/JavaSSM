package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//自动扫描某一包下的Bean
@ComponentScan("com.test.bean")

@Configuration
public class MainConfiguration2 {

}
