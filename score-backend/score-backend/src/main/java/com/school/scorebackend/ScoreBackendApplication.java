package com.school.scorebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.school.scorebackend.mapper")
@SpringBootApplication
public class ScoreBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScoreBackendApplication.class, args);
    }
}