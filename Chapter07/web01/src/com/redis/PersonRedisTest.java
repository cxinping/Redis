package com.redis;

import com.redis.util.SerializeUtil;

import redis.clients.jedis.Jedis;

public class PersonRedisTest {
	private static Jedis jedis = null;

    /**
     * 初始化Jedis对象
     * 
     * @throws Exception
     */
    public PersonRedisTest() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    /**
     * 序列化写对象, 将Person对象写入Redis中
     * 
     */
    public void setObject() {
        jedis.set("person:100".getBytes(),
                SerializeUtil.serialize(new Person(100, "zhangsan")));
        jedis.set("person:101".getBytes(),
                SerializeUtil.serialize(new Person(101, "bruce")));
    }

    /**
     * 反序列化取对象, 用Jedis获取对象
     * 
     */
    public void getObject() {
        byte[] data100 = jedis.get(("person:100").getBytes());
        Person person100 = (Person) SerializeUtil.unserialize(data100);
        System.out.println(String.format("person:100->id=%s,name=%s",
                person100.getId(), person100.getName()));

        byte[] data101 = jedis.get(("person:101").getBytes());
        Person person101 = (Person) SerializeUtil.unserialize(data101);
        System.out.println(String.format("person:101->id=%s,name=%s",
                person101.getId(), person101.getName()));
    }

  public static void main(String[] args) {
        PersonRedisTest rt = new PersonRedisTest();
        rt.setObject();
        rt.getObject();
    }

}
