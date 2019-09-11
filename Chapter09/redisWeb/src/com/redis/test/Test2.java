package com.redis.test;

import redis.clients.jedis.Jedis;

public class Test2 {

	public void testList() {
		// ����redis��������127.0.0.1:6379
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// ��ʼǰ�����Ƴ����е�����
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		// ����key java framework�д����������
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// ��ȡ����������jedis.lrange�ǰ���Χȡ����
		// ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������
		System.out.println(jedis.lrange("java framework", 0, -1));

		jedis.del("java framework");
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "struts");
		jedis.rpush("java framework", "hibernate");
		for(int i=0;i<400;i++){
			jedis.rpush("java framework"+i, "hibernate"+i);
		}
		System.out.println(jedis.lrange("java framework", 0, -1));
	}

	
	public static void main(String[] args) {
		Test2 test = new Test2();
		test.testList();

	}

}
