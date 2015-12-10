package com.zemlyak.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rest")
public class SimpleService {
    private static final Log LOG = LogFactory.getLog(SimpleService.class);

    @RequestMapping("/greeting")
    public SimpleResponse getGreeting(Principal principal){
        LOG.debug("User name from principal: " + principal.getName());
        return new SimpleResponse();
    }

    public static class SimpleResponse{
        public Integer code = 200;
        public String text = "Hello";
    }
}
