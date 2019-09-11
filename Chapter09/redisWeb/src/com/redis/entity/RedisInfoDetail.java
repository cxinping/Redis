package com.redis.entity;


import java.util.HashMap;
import java.util.Map;

/**
 * Redis��Ϣ����ϸ��Ϣ
 * 
 * */
public class RedisInfoDetail {
	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("redis_version", "Redis �������汾");
		map.put("redis_git_sha1", "Git SHA1");
		map.put("redis_git_dirty", "Git dirty flag");
		map.put("os", "Redis ����������������ϵͳ");
		map.put("arch_bits", " �ܹ���32 �� 64 λ��");
		map.put("multiplexing_api", "Redis ��ʹ�õ��¼��������");
		map.put("gcc_version", "���� Redis ʱ��ʹ�õ� GCC �汾");
		map.put("process_id", "���������̵� PID");
		map.put("run_id", "Redis �������������ʶ�������� Sentinel �ͼ�Ⱥ��");
		map.put("tcp_port", "TCP/IP �����˿�");
		map.put("uptime_in_seconds", "�� Redis ��������������������������");
		map.put("uptime_in_days", "�� Redis ��������������������������");
		map.put("lru_clock", " �Է���Ϊ��λ����������ʱ�ӣ����� LRU ����");
		map.put("connected_clients", "�����ӿͻ��˵�������������ͨ���������������ӵĿͻ��ˣ�");
		map.put("client_longest_output_list", "��ǰ���ӵĿͻ��˵��У��������б�");
		map.put("client_longest_input_buf", "��ǰ���ӵĿͻ��˵��У�������뻺��");
		map.put("blocked_clients", "���ڵȴ��������BLPOP��BRPOP��BRPOPLPUSH���Ŀͻ��˵�����");
		
		map.put("used_memory", "�� Redis ������������ڴ�����");
		map.put("keys", "Redis key��ʵʱ����");

		map.put("used_memory_human", "������ɶ��ĸ�ʽ���� Redis ������ڴ�����");
		map.put("used_memory_rss", "�Ӳ���ϵͳ�ĽǶȣ����� Redis �ѷ�����ڴ��������׳Ƴ�פ����С�������ֵ�� top �� ps ����������һ��");
		map.put("used_memory_peak", " Redis ���ڴ����ķ�ֵ(���ֽ�Ϊ��λ)");
		map.put("used_memory_peak_human", "������ɶ��ĸ�ʽ���� Redis ���ڴ����ķ�ֵ");
		map.put("used_memory_lua", "Lua ������ʹ�õ��ڴ��С�����ֽ�Ϊ��λ��");
		map.put("mem_fragmentation_ratio", "sed_memory_rss �� used_memory ֮��ı���");
		map.put("mem_allocator", "�ڱ���ʱָ���ģ� Redis ��ʹ�õ��ڴ�������������� libc �� jemalloc ���� tcmalloc");
	}
	
	private String key;
	
	private String value;
	// ������Ϣ
	private String desctiption;
	// ��ѯ��ʱ���
	private String date; 
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
		this.desctiption = map.get(this.key);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesctiption() {
		return desctiption;
	}
	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}

	public String toString() {
		return "RedisInfoDetail [key=" + key + ", value=" + value + ", desctiption=" + desctiption + ", date=" + date
				+ "]";
	}
	
}
