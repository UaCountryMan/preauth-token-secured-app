package com.zemlyak.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan({"com.zemlyak.web.controller", "com.zemlyak.web.security"})
public class SecuredAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuredAppApplication.class, args);
    }
}
