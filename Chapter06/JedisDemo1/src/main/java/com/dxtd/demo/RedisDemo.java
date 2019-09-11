package com.dxtd.demo;

import redis.clients.jedis.Jedis;

public class RedisDemo {

    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name","xinping");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();

    }


}
