package com.jlu.jtee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.jlu.jtee.mapper")
public class JteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JteeApplication.class, args);
    }

}
