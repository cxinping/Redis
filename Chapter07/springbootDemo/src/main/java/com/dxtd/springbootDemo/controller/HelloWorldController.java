package com.dxtd.springbootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // http://localhost:8080/hello
    @RequestMapping("/hello")
    public String index() {
        return "Hello spring boot";
    }


}
