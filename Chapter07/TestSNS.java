package com.redis;

import redis.clients.jedis.Jedis;

public class TestSNS {

	public static void main(String[] args) {
		Jedis  jedis = new Jedis("127.0.0.1", 6379);
		//��յ�ǰ���ݿ�
		jedis.flushDB();
		
		// zhangsan������
		jedis.sadd("zhangsan", "friend_1");
		jedis.sadd("zhangsan", "friend_2");
		jedis.sadd("zhangsan", "friend_3");
		jedis.sadd("zhangsan", "friend_1");
		System.out.println("zhangsan������ =>"+ jedis.smembers("zhangsan"));
		
		//lisi������
		jedis.sadd("lisi", "friend_1");
		jedis.sadd("lisi", "friend_3");
		jedis.sadd("lisi", "friend_5");
		System.out.println("lisi������ =>"+ jedis.smembers("lisi"));
		
		//ȡzhangsan��lisi�Ĺ�ͬ����
		System.out.println("zhangsan��lisi��ͬ������ =>"+ jedis.sinter("zhangsan","lisi"));
		
		//�ͷ���Դ
		jedis.close();
		
	}

}
