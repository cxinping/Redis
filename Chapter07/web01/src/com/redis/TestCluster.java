package com.redis;

import redis.clients.jedis.Jedis;


public class TestCluster {
	
	public static void setData(){
		
	}

	public static void getData(){
		Jedis jedis_M = new Jedis("192.168.1.12",6379);//����  
        Jedis jedis_S = new Jedis("192.168.1.13",6380);//�ӻ�  
          
        //��ѭ����Ӳ���������ģʽ  
        jedis_S.slaveof("192.168.1.14",6379);  
      
        jedis_M.set("name", "wangwu");//����ȥд  
          
        //�ڴ��ж�д̫�죬��ֹ����д֮ǰ����ɶ�����null�������������һ���ӳ�  
       // Thread.sleep(2000);  
          
        String result = jedis_S.get("name");//�ӻ�ȥ��  
        System.out.println(result);  
	}
	
	 public static void main(String[] args) {
		 getData();
		 
	}

}
