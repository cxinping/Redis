package com.redis;

import redis.clients.jedis.Jedis;


public class TestCluster {
	
	public static void setData(){
		
	}

	public static void getData(){
		Jedis jedis_M = new Jedis("192.168.1.12",6379);//主机  
        Jedis jedis_S = new Jedis("192.168.1.13",6380);//从机  
          
        //遵循“配从不配主”的模式  
        jedis_S.slaveof("192.168.1.14",6379);  
      
        jedis_M.set("name", "wangwu");//主机去写  
          
        //内存中读写太快，防止读在写之前先完成而出现null的情况，这里做一下延迟  
       // Thread.sleep(2000);  
          
        String result = jedis_S.get("name");//从机去读  
        System.out.println(result);  
	}
	
	 public static void main(String[] args) {
		 getData();
		 
	}

}
