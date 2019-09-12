package com.dxtd.monitor.test;


import redis.clients.jedis.Jedis;

public class Test2 {

	public void testList() {
		// 连接redis服务器，127.0.0.1:6379
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// 开始前，先移除所有的内容
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		// 先向key java framework中存放三条数据
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// 再取出所有数据jedis.lrange是按范围取出，
		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
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
