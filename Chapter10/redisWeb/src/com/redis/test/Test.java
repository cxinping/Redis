package com.redis.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.redis.service.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {
	private static volatile JedisPool jedisPool = null;

    private Test() {
    }

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (Test.class) {   //这里使用双端检测设计模式
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制，
                    //如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted
                    poolConfig.setMaxIdle(32);  //设置剩余连接各数，如果小于这个就会抛异常
                    //表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛JedisConnectionException
                    //获得一个jedis实例的时候是否检查连接可用性(ping()),如果为true，则得到的jedis实例均是可用的
                    poolConfig.setTestOnBorrow(true); 
                    jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
                }
            }
        }
        return jedisPool;
    }
    /**
     * 释放
     * @param jedisPool 释放哪个池中
     * @param jedis        的哪个对象
     */
    public static void release(JedisPool jedisPool,Jedis jedis){
        if(null != jedis){
            jedisPool.returnResourceObject(jedis);
        }
    }

    public static void main(String[] args) {
//    	JedisPool jedisPool =	Test.getJedisPoolInstance();
//    	Jedis jedis = jedisPool.getResource();
//    	System.out.println( jedis );
    	
    	RedisService redisService = new RedisService();
    	List keys = new ArrayList();
    	//redis 内存实时占用情况
    	keys.add("used_memory_human");
    	//redis key的实时数量
    	keys.add("db");
    	System.out.println(  JSON.toJSONString(redisService.getRedisInfo(keys) ));

    	//    	RedisUtil redisUtil = new RedisUtil();
//    	redisUtil.getRedisInfo();
    	
    }

}
