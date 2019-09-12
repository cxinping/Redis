package com.dxtd.monitor;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class Test {

	public String getInfo() {
		String infoContent = null;
		Jedis jedis = null;
		try {
			jedis = RedisUtils.getJedis();
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
		Statement stat = new Statement();
		stat.setUsed_cpu_sys(getIntValue(infoMap, "CPU.used_cpu_sys"));
		stat.setUsed_cpu_user(getIntValue(infoMap, "CPU.used_cpu_user"));

		stat.setBlocked_clients(getIntValue(infoMap, "Clients.blocked_clients"));
		stat.setConnected_clients(getIntValue(infoMap, "Clients.connected_clients"));
		stat.setUsed_memory(getIntValue(infoMap, "Memory.used_memory"));
		stat.setUsed_memory_rss(getIntValue(infoMap, "Memory.used_memory_rss"));

		//
		double cmd = getIntValue(infoMap, "Stats.total_commands_processed");
		double exp = getIntValue(infoMap, "Stats.expired_keys");
		double evt = getIntValue(infoMap, "Stats.evicted_keys");

		double hit = getIntValue(infoMap, "Stats.keyspace_hits");
		double mis = getIntValue(infoMap, "Stats.keyspace_misses");

		// long lastTs = stat.getTimestamp();
		long thisTs = System.currentTimeMillis();
		System.out.println(stat);
	}

	public static void main(String[] args) {
		Test test = new Test();
		String info = test.getInfo();
		// Map map = test.parseInfo(info);
		// System.out.println("map=" +map);
		test.transData(info);

	}

}
