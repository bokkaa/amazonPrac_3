package com.example.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class S3Application {

    public static void main(String[] args) {
        SpringApplication.run(S3Application.class, args);
    }

}
