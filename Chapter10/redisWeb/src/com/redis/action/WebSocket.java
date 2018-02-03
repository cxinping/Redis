package com.redis.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.redis.entity.RedisInfoDetail;
import com.redis.service.RedisService;
import com.redis.util.RedisUtil;

/**
 * @ServerEndpoint ע����һ�����ε�ע�⣬���Ĺ�����Ҫ�ǽ�Ŀǰ���ඨ���һ��websocket��������,
 *                 ע���ֵ�������ڼ����û����ӵ��ն˷���URL��ַ,�ͻ��˿���ͨ�����URL�����ӵ�WebSocket��������
 */
@ServerEndpoint("/websocket")
public class WebSocket {
	    // ��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
	    private static int onlineCount = 0;

	    // concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
	    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

	    // ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	    private Session session;

	    /**
	     * ���ӽ����ɹ����õķ���
	     * 
	     * @param session
	     *            ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	     */
	    @OnOpen
	    public void onOpen(Session session) {
	        this.session = session;
	        webSocketSet.add(this); // ����set��
	        addOnlineCount(); // ��������1
	        System.out.println("�������Ӽ��룡��ǰ��������Ϊ" + getOnlineCount());
	    }

	    /**
	     * ���ӹرյ��õķ���
	     */
	    @OnClose
	    public void onClose() {
	        webSocketSet.remove(this); // ��set��ɾ��
	        subOnlineCount(); // ��������1
	        System.out.println("��һ���ӹرգ���ǰ��������Ϊ" + getOnlineCount());
	    }

	    /**
	     * �յ��ͻ�����Ϣ����õķ���
	     * 
	     * @param message
	     *            �ͻ��˷��͹�������Ϣ
	     * @param session
	     *            ��ѡ�Ĳ���
	     */
	    @OnMessage
	    public void onMessage(String message, Session session) {
	        System.out.println("���Կͻ��˵���Ϣ:" + message);
	        // Ⱥ����Ϣ
	        for (WebSocket item : webSocketSet) {
	            try {
	            	
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
	        		//System.out.println(JSON.toJSONString( details));
	        		//String keysVal = JSON.toJSONString(redisService.getKeysValue(info));
	        		//System.out.println(keysVal);
	            	
	                //item.sendMessage("The Server received a message =>"+message);
	                
	                item.sendMessage(JSON.toJSONString(details) );
	                
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	                continue;
	            }
	        }
	    }

	    /**
	     * ��������ʱ����
	     * 
	     * @param session
	     * @param error
	     */
	    @OnError
	    public void onError(Session session, Throwable error) {
	        System.out.println("��������");
	        error.printStackTrace();
	    }

	    /**
	     * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ���ӵķ�����
	     * 
	     * @param message
	     * @throws IOException
	     */
	    public void sendMessage(String message) throws IOException {
	        this.session.getBasicRemote().sendText(message);
	    }

	    public static synchronized int getOnlineCount() {
	        return onlineCount;
	    }

	public static synchronized void addOnlineCount() {
	    	WebSocket.onlineCount++;
	    }

	    public static synchronized void subOnlineCount() {
	    	WebSocket.onlineCount--;
	    }

}