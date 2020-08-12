package com.dxtd.demo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

public class RedisClusterDemo {

    public static void testJedisCluster()  {
        JedisPoolConfig config = new JedisPoolConfig();
        config = new JedisPoolConfig();
        config.setMaxTotal(60000);//设置最大连接数
        config.setMaxIdle(1000); //设置最大空闲数
        config.setMaxWaitMillis(3000);//设置超时时间
        config.setTestOnBorrow(true);

        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.11.15", 8001));
        nodes.add(new HostAndPort("192.168.11.15", 8002));
        nodes.add(new HostAndPort("192.168.11.15", 8003));
        nodes.add(new HostAndPort("192.168.11.15", 8004));
        nodes.add(new HostAndPort("192.168.11.15", 8005));
        nodes.add(new HostAndPort("192.168.11.15", 8006));
        JedisCluster cluster = new JedisCluster(nodes, config);
        cluster.set("book", "redis");
        System.out.println("集群测试  key=book , value=" + cluster.get("book"));
        cluster.close();
    }

    public static void main(String[] args){
        testJedisCluster();
    }

}
