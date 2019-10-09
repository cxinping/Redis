package com.dxtd.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolDemo {

        public static void main(String[] args){
            //创建连接池配置对象：
            JedisPoolConfig jpc = new JedisPoolConfig();
            //设置最大闲置个数
            jpc.setMaxIdle(30);
            //设置最小闲置个数：
            jpc.setMinIdle(10);
            //设置最大的连接数
            jpc.setMaxTotal(50);
            //创建连接池对象  host:连接redis主机IP ；port:redis的默认端口
            JedisPool jedisPool = new JedisPool(jpc, "127.0.0.1", 6379);
            Jedis resource = null;
            try{

                //获取连接资源
                resource = jedisPool.getResource();
                //放值：
                resource.set("name", "hello redis");
                //取值：
                String name = resource.get("name");
                System.out.println("name=" + name);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if (resource != null) {
                    //这里使用的close不代表关闭连接，指的是归还资源
                    jedisPool.close();
                }
            }

        }

}
