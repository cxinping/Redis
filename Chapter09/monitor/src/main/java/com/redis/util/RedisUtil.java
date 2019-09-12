package com.redis.util;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private static JedisPool jedisPool;

	/**
	 * �������ӳ� ��ʵ������һ������ò���ȱ��ȡ������
	 * 
	 */
	private static void createJedisPool() {

		// �������ӳ����ò���
		JedisPoolConfig config = new JedisPoolConfig();

		// ���ÿռ�����
		config.setMaxIdle(10);

		// �������ӳ�
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);

	}

	/**
	 * �ڶ��̻߳���ͬ����ʼ��
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null)
			createJedisPool();
	}

	/**
	 * ��ȡһ��jedis ����
	 * 
	 * @return
	 */
	static {
		if (jedisPool == null)
			poolInit();

	}

	/**
	 * �黹һ������
	 * 
	 * @param jedis
	 */
	public static void returnRes(Jedis jedis) {
		//jedisPool.returnResource(jedis);
	}

	/**
	 * ��ȡredis ��������Ϣ
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
			// ���������ӳ�
			if(null != jedis)
			jedis.close();
		}
	}

}
