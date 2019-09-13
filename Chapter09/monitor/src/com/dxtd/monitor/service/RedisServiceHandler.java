package com.dxtd.monitor.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dxtd.monitor.action.WebSocketController;
import com.dxtd.monitor.entity.RedisInfoDetail;
import com.dxtd.monitor.util.RedisUtil;

public class RedisServiceHandler implements Runnable {

	private WebSocketController webSocket;
	
	public RedisServiceHandler(WebSocketController webSocket){
		this.webSocket = webSocket;
	}
	
	public void run() {
		
		while(true){
			            
    		try {
//    			RedisService redisService = new RedisService();
//        		RedisUtil redisUtil = new RedisUtil();
//        		String info = redisUtil.getRedisInfo();
//        		// redis 内存实时占用情况
//        		RedisInfoDetail memoryDetail = redisService.getRedisInfo(info, "used_memory_human");
//        		// redis key的实时数量
//        		RedisInfoDetail keysDetail = redisService.getKeysValue(info);
//        		List<RedisInfoDetail> details = new ArrayList<RedisInfoDetail>(); 
//        		details.add(memoryDetail );
//        		details.add(keysDetail );
//        		
//    			//群发消息
//				webSocket.sendMessage(JSON.toJSONString(details) );
				
				MonitorService monitor = new MonitorService();
				String info = monitor.getInfo();
				Map<String,Object> redisInfo = monitor.getRedisInfo(info);
    			//群发消息
				webSocket.sendMessage(JSON.toJSONString(redisInfo) );				
				//每5秒发送一次消息，以便页面更新数据
				Thread.sleep(1000 * 5 );
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
