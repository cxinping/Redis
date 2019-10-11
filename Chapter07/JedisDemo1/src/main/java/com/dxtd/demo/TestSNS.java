package com.dxtd.demo;

import redis.clients.jedis.Jedis;

public class TestSNS {

    public static void main(String[] args) {
        Jedis  jedis = new Jedis("127.0.0.1", 6379);
        //清空当前数据库
        jedis.flushDB();

        // zhangsan的朋友
        jedis.sadd("zhangsan", "friend_1");
        jedis.sadd("zhangsan", "friend_2");
        jedis.sadd("zhangsan", "friend_3");
        System.out.println("zhangsan的朋友 =>"+ jedis.smembers("zhangsan"));

        //lisi的朋友
        jedis.sadd("lisi", "friend_1");
        jedis.sadd("lisi", "friend_3");
        jedis.sadd("lisi", "friend_5");
        System.out.println("lisi的朋友 =>"+ jedis.smembers("lisi"));

        //取zhangsan和lisi的共同好友
        System.out.println("zhangsan和lisi共同的朋友 =>"+ jedis.sinter("zhangsan","lisi"));
        //释放资源
        jedis.close();
    }

}
