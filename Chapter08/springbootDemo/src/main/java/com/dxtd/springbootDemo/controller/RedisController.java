package com.dxtd.springbootDemo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public Map setAndGetValue(String name, String value){
        System.out.println( redisTemplate );
        redisTemplate.opsForValue().set(name,value);
        Map result = new HashMap();
        String getValue =  (String) redisTemplate.opsForValue().get(name);
        result.put(name ,getValue);
        return result;
    }

}