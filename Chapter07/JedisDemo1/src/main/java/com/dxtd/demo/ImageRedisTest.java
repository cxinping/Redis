package com.dxtd.demo;

import java.net.MalformedURLException;
import java.net.URL;
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
    public void saveImage() {
        URL url = null;
        try {
            url = new URL("https://graph.baidu.com/thumb/691874602,911901597.jpg");
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
    public void getImage() {
        System.out.println(jedis.get(("image:e:/pandas.jpg")));
        String encoderStr = jedis.get("image:e:/pandas.jpg");
        Base64ImageUtils.decodeBase64ToImage(encoderStr, "E:/", "pandas2.jpg");

    }

    public static void main(String[] args) {
        ImageRedisTest test = new ImageRedisTest();
        test.saveImage();
        //test.getImage();
    }






}
