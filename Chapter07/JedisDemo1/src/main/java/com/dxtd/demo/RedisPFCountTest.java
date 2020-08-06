package com.dxtd.demo;

import redis.clients.jedis.Jedis;

public class RedisPFCountTest {

    public static void testHyperLogLog(){
        Jedis jedis = new Jedis("192.168.11.12",6379);
        for (int i = 0; i < 100 * 10000; i++) {
            //System.out.println("i=" +  i);
            jedis.pfadd("user:login:2020080622", "user-"+ i );
        }

        long total = jedis.pfcount("user:login:2020080622");
        System.out.printf("key=user:login:2020080622 count=%d", total);

        jedis.close();
    }

    public static void testList() {
        // 连接redis服务器，127.0.0.1:6379
        Jedis jedis = new Jedis("192.168.11.12", 6379);
        // 开始前，先移除所有的内容
        jedis.del("java");
        System.out.println(jedis.lrange("java", 0, -1));

        for(int i=0;i< 10000 ;i++){
            jedis.lpush("java", "spring-"+ i);

        }

        jedis.close();
    }

    public static void main(String[] args) {
       // testList();
        testHyperLogLog();

    }

}
