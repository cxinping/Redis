package com.xinping.RedisMonitor.util;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static JedisPool jedisPool;

    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     *
     */
    private static void createJedisPool() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //指定连接池中最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        //链接池中创建的最大连接数
        jedisPoolConfig.setMaxTotal(100);
        //设置创建链接的超时时间
        jedisPoolConfig.setMaxWaitMillis(2000);
        //表示连接池在创建链接的时候会先测试一下链接是否可用，这样可以保证连接池中的链接都可用的。
        jedisPoolConfig.setTestOnBorrow(true);

        // 创建连接池
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    static {
        if (jedisPool == null)
            poolInit();

    }

    /**
     * 归还一个连接
     *
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        jedis.close();
    }

    public static Jedis getJedis() {
        if (jedisPool == null)
            poolInit();

        Jedis jedis = jedisPool.getResource();
        return jedis;
    }


    /**
     * 获取redis 服务器信息
     *
     * @param String
     */
    public String getRedisInfo() {
        if (jedisPool == null)
            poolInit();

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Client client = jedis.getClient();
            client.info();
            String info = client.getBulkReply();
            return info;
        } finally {
            // 返还到连接池
            if(null != jedis)
                jedis.close();
        }
    }

}
