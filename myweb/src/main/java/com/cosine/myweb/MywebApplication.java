package com.cosine.myweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cosine.myweb.dao.*")
public class MywebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MywebApplication.class, args);
    }
}
