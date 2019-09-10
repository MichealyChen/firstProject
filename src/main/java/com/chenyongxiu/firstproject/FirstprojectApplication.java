package com.chenyongxiu.firstproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.chenyongxiu.firstproject.dao"})
public class FirstprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstprojectApplication.class, args);
    }

}
