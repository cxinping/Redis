package com.dxtd.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import static sun.management.jmxremote.ConnectorBootstrap.PropertyNames.HOST;

public class RedisClusterDemo {

    public static void testJedisCluster()  {
        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.11.10", 7000));
        nodes.add(new HostAndPort("192.168.11.10", 7001));
        nodes.add(new HostAndPort("192.168.11.10", 7002));
        nodes.add(new HostAndPort("192.168.11.11", 7003));
        nodes.add(new HostAndPort("192.168.11.11", 7004));
        nodes.add(new HostAndPort("192.168.11.11", 7005));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("cluster-key", "cluster-value");
        System.out.println("集群测试 ： " + cluster.get("cluster-key"));
        cluster.close();
    }

    public static void main(String[] args){
        testJedisCluster();
    }

}
