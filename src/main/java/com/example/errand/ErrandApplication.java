package com.example.errand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


@SpringBootApplication
@MapperScan("com.example.errand.mapper")
public class ErrandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrandApplication.class, args);
    }

}
