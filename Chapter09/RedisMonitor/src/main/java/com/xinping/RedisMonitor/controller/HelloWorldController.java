package com.xinping.RedisMonitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloWorldController {


    @RequestMapping("/hello")
    public String index()
    {
        return "Hello World " + new Date();
    }


}
