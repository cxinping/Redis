package com.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisJava {

	public void testString() {
		// ����redis��������127.0.0.1:6379
		Jedis jedis = new Jedis("127.0.0.1", 6379);

		// -----�������----------
		jedis.set("name", "wang");// ��key-->name�з�����value-->wang
		System.out.println(jedis.get("name"));// ִ�н����wang

		jedis.append("name", " is my lover"); // ƴ��
		System.out.println(jedis.get("name"));

		jedis.del("name"); // ɾ��ĳ����
		System.out.println(jedis.get("name"));
		// ���ö����ֵ��
		jedis.mset("name", "lisi", "age", "23", "qq", "476777XXX");
		jedis.incr("age"); // ���м�1����
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));

	}

	/**
	 * redis����Map
	 */
	public void testMap() {
		// -----�������----------
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "wang");
		map.put("age", "22");
		map.put("qq", "123456");

		// ����redis��������127.0.0.1:6379
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.hmset("user", map);
		// ȡ��user�е�name��ִ�н��:[minxr]-->ע������һ�����͵�List
		// ��һ�������Ǵ���redis��map�����key����������Ƿ���map�еĶ����key�������key���Ը�������ǿɱ����
		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");

		// ɾ��map�е�ĳ����ֵ
		jedis.hdel("user", "age");
		System.out.println(jedis.hmget("user", "age")); // ��Ϊɾ���ˣ����Է��ص���null
		System.out.println(jedis.hlen("user")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���2
		System.out.println(jedis.exists("user"));// �Ƿ����keyΪuser�ļ�¼ ����true
		System.out.println(jedis.hkeys("user"));// ����map�����е�����key
		System.out.println(jedis.hvals("user"));// ����map�����е�����value

		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
	}

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
		System.out.println(jedis.lrange("java framework", 0, -1));
	}

	public void testSet() {
		// ����redis��������127.0.0.1:6379
		Jedis jedis = new Jedis("127.0.0.1", 6379);

		// ���
		jedis.sadd("user", "lisi");
		jedis.sadd("user", "wangwu");
		jedis.sadd("user", "ling");
		jedis.sadd("user", "zhangsan");
		jedis.sadd("user", "who");
		// �Ƴ�noname
		jedis.srem("user", "who");
		System.out.println(jedis.smembers("user"));// ��ȡ���м����value
		System.out.println(jedis.sismember("user", "who"));// �ж� who
															// �Ƿ���user���ϵ�Ԫ��
		System.out.println(jedis.srandmember("user"));
		System.out.println(jedis.scard("user"));// ���ؼ��ϵ�Ԫ�ظ���
	}

	public void testSort()   {
		// ����redis��������127.0.0.1:6379
		Jedis jedis = new Jedis("127.0.0.1", 6379);

		// jedis ����
		// ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�
		jedis.del("a");// ��������ݣ��ټ������ݽ��в���
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
		System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //�����������
		System.out.println(jedis.lrange("a", 0, -1));
	}

	public static void main(String[] args) {
		RedisJava test = new RedisJava();
		test.testSort();

	}

}
