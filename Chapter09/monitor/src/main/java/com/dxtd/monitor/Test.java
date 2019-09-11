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

	public static void main(String[] args) {
		Test test = new Test();
		String info = test.getInfo();
		// System.out.println(info);

		Map map = test.parseInfo(info);
		System.out.println("map=" +map);
	}

}
