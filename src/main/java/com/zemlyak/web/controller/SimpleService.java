package com.zemlyak.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rest")
public class SimpleService {
    @RequestMapping("/greeting")
    public SimpleResponse getGreeting(Principal principal){
        System.out.println("[QQQQQQQQQQ] loadUserByUsername: " + principal.getName());
        return new SimpleResponse();
    }

    public static class SimpleResponse{
        public Integer code = 200;
        public String text = "Hello";
    }
}
