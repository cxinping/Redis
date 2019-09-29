package com.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    
    private RedisUtils(){
    
    }
    
    private static  JedisPool jedisPool = null;
    //��ȡ����
    public static synchronized Jedis getJedis(){
        if(jedisPool==null){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //ָ�����ӳ���������������
            jedisPoolConfig.setMaxIdle(10);
            //���ӳ��д��������������
            jedisPoolConfig.setMaxTotal(100);
            //���ô������ӵĳ�ʱʱ��
            jedisPoolConfig.setMaxWaitMillis(2000);
            //��ʾ���ӳ��ڴ������ӵ�ʱ����Ȳ���һ�������Ƿ���ã��������Ա�֤���ӳ��е����Ӷ����õġ�
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        }
        return jedisPool.getResource();
    }
    
    //��������
    public static void returnResource(Jedis jedis){
        jedisPool.returnResourceObject(jedis);
    }

    public static void main(String[] args) {
    	Jedis jedis = RedisUtils.getJedis();
    	jedis.set("name","����" );
    	RedisUtils.returnResource(jedis);
	}
    
}