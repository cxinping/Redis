package com.redis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import com.redis.util.Base64ImageUtils;
import com.redis.util.FileUtil;

import redis.clients.jedis.Jedis;

public class ImageRedisTest {
	private static Jedis jedis = null;

	/**
	 * 初始化Jedis对象
	 * 
	 * @throws Exception
	 */
	public ImageRedisTest() {
		jedis = new Jedis("127.0.0.1", 6379);
	}

	/**
	 * 将图片对象写入Redis中
	 * 
	 */
	public void setObject() {
		URL url = null;
		try {
			url = new URL("http://p1s8k4lys.bkt.clouddn.com/pandas.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String encoderStr = Base64ImageUtils.encodeImgageToBase64(url);
		System.out.println(encoderStr);

		jedis.set("image:e:/pandas.jpg".getBytes(), encoderStr.getBytes());
	}

	/**
	 * 从 Redis中获得图片，保存到本地
	 * 
	 */
	public void getObject() {
		System.out.println(jedis.get(("image:e:/pandas.jpg")));
		String encoderStr = jedis.get("image:e:/pandas.jpg");
		Base64ImageUtils.decodeBase64ToImage(encoderStr, "E:/", "pandas2.jpg");

	}

	public static void main(String[] args) {
		ImageRedisTest test = new ImageRedisTest();
		// test.setObject();
		test.getObject();

	}

}
