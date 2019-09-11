package com.redis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.redis.action.WebSocketController;
import com.redis.entity.RedisInfoDetail;
import com.redis.util.RedisUtil;

public class RedisServiceHandler implements Runnable {

	private WebSocketController webSocket;
	
	public RedisServiceHandler(WebSocketController webSocket){
		this.webSocket = webSocket;
	}
	
	public void run() {
		
		while(true){
			RedisService redisService = new RedisService();
    		RedisUtil redisUtil = new RedisUtil();
    		String info = redisUtil.getRedisInfo();
    		// redis �ڴ�ʵʱռ�����
    		RedisInfoDetail memoryDetail = redisService.getRedisInfo(info, "used_memory_human");
    		// redis key��ʵʱ����
    		RedisInfoDetail keysDetail = redisService.getKeysValue(info);
    		List<RedisInfoDetail> details = new ArrayList<RedisInfoDetail>(); 
    		details.add(memoryDetail );
    		details.add(keysDetail );
            
    		try {
    			//Ⱥ����Ϣ
				webSocket.sendMessage(JSON.toJSONString(details) );
				
				//ÿ10�뷢��һ����Ϣ���Ա�ҳ���������
				Thread.sleep(1000 * 10 );
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
