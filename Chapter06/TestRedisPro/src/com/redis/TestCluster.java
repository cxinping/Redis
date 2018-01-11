package com.redis;

import java.util.HashSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


public class TestCluster {
	 
	 public static void main(String[] args) {
			HashSet<HostAndPort> set = new HashSet<HostAndPort>();
			set.add(new HostAndPort("192.168.1.13", 7000));
			set.add(new HostAndPort("192.168.1.13", 7001));
			set.add(new HostAndPort("192.168.1.13", 7002));
			
			set.add(new HostAndPort("192.168.1.14", 7003));
			set.add(new HostAndPort("192.168.1.14", 7004));
			set.add(new HostAndPort("192.168.1.14", 7005));
			
			
			JedisCluster cluster = new JedisCluster(set);
			//redis内部会创建连接池，从连接池中获取连接使用，然后再把连接返回给连接池
			cluster.set("greeting", "hello redis");
			cluster.set("name", "wangwu");
			cluster.set("age", "20" );
			cluster.set("address", "beijing" );
			
		}

}
