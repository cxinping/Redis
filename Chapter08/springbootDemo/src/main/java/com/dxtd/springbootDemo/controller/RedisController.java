package com.dxtd.springbootDemo.controller;

import com.dxtd.springbootDemo.RedisService;
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

    @Resource
    private RedisService service;

    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public Map setAndGetValue(String name, String value){
        System.out.println( "name="+ name + ",value=" + value );
        redisTemplate.opsForValue().set(name,value);
        Map result = new HashMap();
        String getValue =  (String) redisTemplate.opsForValue().get(name);
        result.put(name ,getValue);
        return result;
    }

    @RequestMapping("/redis/setAndGet2")
    @ResponseBody
    public Map setAndGetValueV2(String name,String value){
        service.set(name,value);
        Map result = new HashMap();
        String getValue =  service.get(name).toString();;
        result.put(name ,getValue);
        return result;
    }

}