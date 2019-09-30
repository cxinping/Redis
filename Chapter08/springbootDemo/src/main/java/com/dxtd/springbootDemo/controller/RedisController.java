package com.dxtd.springbootDemo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public String setAndGetValue(String name,String value){
        redisTemplate.opsForValue().set(name,value);
        return (String) redisTemplate.opsForValue().get(name);
    }

}