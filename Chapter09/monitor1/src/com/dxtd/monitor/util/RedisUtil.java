package com.dxtd.monitor.util;


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

		// 建立连接池配置参数
		JedisPoolConfig config = new JedisPoolConfig();

		// 设置空间连接
		config.setMaxIdle(10);

		// 创建连接池
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);

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
		//jedisPool.returnResource(jedis);
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
