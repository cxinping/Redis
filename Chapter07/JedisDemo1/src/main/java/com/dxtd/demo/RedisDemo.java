package com.dxtd.demo;

import redis.clients.jedis.Jedis;

public class RedisDemo {

    public static void main(String[] args){
        //1.生成一个Jedis对象，这个对象负责和指定的Redis节点进行通信
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.jedis执行set操作
        jedis.set("name","xinping");
        //3.jedis执行get操作
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();

    }
}
