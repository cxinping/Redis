package com.dxtd.monitor;

import redis.clients.jedis.Jedis;

public class Test {

	public String getInfo() {
        String infoContent = null;
        Jedis jedis = null;
        try {
        	jedis = RedisUtils.getJedis();
            infoContent = jedis.info();
        }catch(Exception e) {
        	e.printStackTrace();
        } 
        finally {
            if (jedis != null) {
            	jedis.close();
            }
        }
        return infoContent;
    }

	public static void main(String[] args) {
		Test test = new Test();
		String info = test.getInfo();
		System.out.println(info);
		

	}

}
