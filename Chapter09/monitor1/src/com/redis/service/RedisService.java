package com.redis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redis.entity.RedisInfoDetail;
import com.redis.util.RedisUtil;
import com.redis.util.Tools;

public class RedisService {

	public RedisUtil redisUtil = new RedisUtil();

	public List<RedisInfoDetail> getRedisInfo() {
		// 获取redis服务器信息
		String info = redisUtil.getRedisInfo();
		List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = null;
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				rif = new RedisInfoDetail();
				String s = strs[i];
				String[] str = s.split(":");
				if (str != null && str.length > 1) {
					String key = str[0];
					String value = str[1];
					rif.setKey(key);
					rif.setValue(value);
					ridList.add(rif);
				}
			}
		}
		return ridList;
	}

	public RedisInfoDetail getRedisInfo(String redisInfo, String index) {
		// 获取redis服务器信息
		String info = redisUtil.getRedisInfo();
		List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = null;
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				rif = new RedisInfoDetail();

				String s = strs[i];
				String[] str = s.split(":");
				if (str != null && str.length > 1 && index.contains(str[0])) {
					String key = str[0];
					String value = str[1].trim();
					rif.setKey(key);
					//由 Redis 分配器分配的内存总量，以字节（byte）为单位，转换为M 
					//rif.setValue(value);
					rif.setValue(Tools.transByteToMBSize(Integer.valueOf(value)));
					rif.setDate(Tools.getCurrntTime());

					ridList.add(rif);
					return rif;
				}
			}
		}
		return rif;
	}

	public RedisInfoDetail getKeysValue(String redisInfo) {
		// 获取redis服务器信息
		String info = redisUtil.getRedisInfo();
		//List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = new RedisInfoDetail();
		//String keys = "keys";
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("keys", 0);
		
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				String s = strs[i];

				if (s.indexOf(":keys") > -1) {
					String[] str = s.split(",");
					if (null != str) {
						String[] dbs = str[0].split(":");
						String[] dbKeys = dbs[1].split("=");
						String key = dbKeys[0];
						Integer value = Integer.valueOf(dbKeys[1]);

						Integer keysVal = map.get("keys");
						if (null == keysVal) {
							map.put("keys", value);
						} else {
							map.put("keys", keysVal + value);
						}

						rif.setKey(key);
						rif.setValue(map.get("keys") + "");
						rif.setDate(Tools.getCurrntTime());
					}
				}else{
					rif.setKey("keys");
					rif.setValue(map.get("keys") + "");
					rif.setDate(Tools.getCurrntTime());
				}
			}
		} 
		return rif;
	}



	// 获取当前redis使用内存大小情况
	public Map<String, Object> getMemeryInfo() {
		String[] strs = redisUtil.getRedisInfo().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory")) {
				map = new HashMap<String, Object>();
				map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", new Date().getTime());
				break;
			}
		}
		return map;
	}

	public static void main(String[] args) {
		RedisService service = new RedisService();
		List<RedisInfoDetail>  list = service.getRedisInfo();
		System.out.println(list);
		
	}

}
