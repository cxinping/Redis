package com.dxtd.demo;

import redis.clients.jedis.Jedis;

public class RedisDemo2 {
    public static void testString() {
        // 连接Redis服务器，127.0.0.1:6379
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // -----添加数据----------
        jedis.set("name", "wang");
        System.out.println(jedis.get("name"));// 执行结果：wang

        jedis.append("name", " is my friend"); // 拼接
        System.out.println(jedis.get("name"));

        jedis.del("name"); // 删除某个键
        System.out.println(jedis.get("name"));
        // 设置多个键值对
        jedis.mset("name", "xinping", "age", "35", "qq", "759949947");
        jedis.incr("age"); // 进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));

    }

    public static void  main(String[] args){
        testString();

    }

}
