package com.dxtd.monitor.service;

import java.util.HashMap;
import java.util.Map;

import com.dxtd.monitor.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class MonitorService {

	public String getInfo() {
		String infoContent = null;
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			infoContent = jedis.info();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return infoContent;
	}

	Double getIntValue(Map<String, String> infoMap, String key) {
		if (null == infoMap)
			return 0.0;

		Double value = Double.valueOf(infoMap.get(key));
		return value;
	}

	public Map parseInfo(String content) {
		String[] lines = content.split("\n");
		Map infoMap = new HashMap();
		String part = null;
		for (String line : lines) {
			if (line.isEmpty()) {
				continue;
			}

			if (line.startsWith("#")) {
				part = line.replace("#", "").trim();
				continue;
			}

			int index = line.indexOf(':');
			if (index >= 0) {
				infoMap.put(part + "." + line.substring(0, index), line.substring(index + 1));
			}
		}

		return infoMap;
	}

	public void transData(String infoContent) {
		Map<String, String> infoMap = parseInfo(infoContent);
		// 内核空间占用CPU百分比
		double ucs = getIntValue(infoMap, "CPU.used_cpu_sys");
		// 用户空间占用CPU百分比
		double ucu = getIntValue(infoMap, "CPU.used_cpu_user");
		// 阻塞客户端数量
		double cbc = getIntValue(infoMap, "Clients.blocked_clients");
		// 连接客户端数量
		double ccc = getIntValue(infoMap, "Clients.connected_clients");
		// 使用总内存
		double mum = getIntValue(infoMap, "Memory.used_memory");
		// 使用物理内存
		double mur = getIntValue(infoMap, "Memory.used_memory_rss");
		// 运行以来执行过的命令的总数量
		double cmd = getIntValue(infoMap, "Stats.total_commands_processed");
		// 每秒过期key数量
		double exp = getIntValue(infoMap, "Stats.expired_keys");
		// 每秒淘汰key数量
		double evt = getIntValue(infoMap, "Stats.evicted_keys");
		// 每秒命中数量
		double hit = getIntValue(infoMap, "Stats.keyspace_hits");
		// 每秒丢失数量
		double mis = getIntValue(infoMap, "Stats.keyspace_misses");

		long thisTs = System.currentTimeMillis();

		System.out
				.println("ucs=" + ucs + ",ucu=" + ucu + ",cbc=" + cbc + ",ccc=" + ccc + ",mum=" + mum + ",mur=" + mur);
		System.out.println("cmd=" + cmd + ",exp=" + exp + ",evt=" + evt + ",hit=" + hit + ",mis=" + mis);

	}

	public static void main(String[] args) {
		MonitorService monitor = new MonitorService();
		String info = monitor.getInfo();
		// Map map = test.parseInfo(info);
		System.out.println("map=" + info);
		monitor.transData(info);

	}

}
