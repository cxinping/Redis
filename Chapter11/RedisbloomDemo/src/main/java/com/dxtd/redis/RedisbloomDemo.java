package com.dxtd.redis;

import io.rebloom.client.Client;

public class RedisbloomDemo {
    public static void main(String[] args) {
        String userIdBloomKey = "userid";
        // 创建客户端，jedis实例
        Client client = new Client("192.168.11.12", 6379);

        // 创建一个有初始值和出错率的过滤器
        client.createFilter(userIdBloomKey,100000,0.01);
        // 新增一个<key,value>
        boolean userid1 = client.add(userIdBloomKey,"101310111");
        System.out.println("userid1 add " + userid1);

        // 批量新增values
        boolean[] booleans = client.addMulti(userIdBloomKey, "101310222", "101310333", "101310444");

        for (boolean flag : booleans){
            System.out.println("add multi result " + flag );
        }

        // 某个value是否存在
        boolean exists = client.exists(userIdBloomKey, "101310111");
        System.out.println("101310111 是否存在 " + exists);

        //某批value是否存在
        boolean existsBoolean[] = client.existsMulti(userIdBloomKey, "101310111","101310222", "101310333","101310555");

        for (boolean flag : existsBoolean){
            System.out.println("某批value是否存在 " + flag );
        }
    }

}
