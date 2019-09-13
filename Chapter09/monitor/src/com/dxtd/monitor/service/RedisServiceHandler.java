package com.dxtd.monitor.service;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dxtd.monitor.action.WebSocketController;

public class RedisServiceHandler implements Runnable {

	private WebSocketController webSocket;
	
	public RedisServiceHandler(WebSocketController webSocket){
		this.webSocket = webSocket;
	}
	
	public void run() {		
		while(true){			            
    		try {
				
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
