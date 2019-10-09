package com.dxtd.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import java.util.List;

public class RedisTransDemo
{
    public static void jedisNormal() {
        Jedis jedis = new Jedis("localhost");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            String result = jedis.set("n:" + i,   ""+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds");
        jedis.disconnect();
    }


    public static void jedisTrans() {
        Jedis jedis = new Jedis("localhost");
        long start = System.currentTimeMillis();
        Transaction tx = jedis.multi();
        for (int i = 0; i < 100; i++) {
            tx.set("t:" + i,  i + "");
        }
        List<Object> results = tx.exec();
        long end = System.currentTimeMillis();
        System.out.println("Transaction SET: " + ((end - start)/1000.0) + " seconds");
        jedis.disconnect();
    }

    public static  void jedisPipelined() {
        Jedis jedis = new Jedis("localhost");
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            pipeline.set("p:" + i, "" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " seconds");
        jedis.disconnect();
    }

    public static  void jedisCombPipelineTrans() {
        Jedis jedis = new Jedis("localhost");
        long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("" + i, "" + i);
        }
        pipeline.exec();
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined transaction: " + ((end - start) / 1000.0) + " seconds");
        jedis.disconnect();
    }


    public static void main(String[] args){
        //jedisNormal();
        //jedisTrans();
        //jedisPipelined();
        jedisCombPipelineTrans();

    }

}
