package com.dbl.godc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dbl.godc.mapper")

public class GodCApplication {

    public static void main(String[] args) {
        SpringApplication.run(GodCApplication.class, args);
    }

}
