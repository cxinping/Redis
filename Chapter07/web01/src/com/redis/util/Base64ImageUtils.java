package com.redis.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;  

public class Base64ImageUtils {
	  /**
     * ������ͼƬ����Base64λ����
     * 
     * @param imageUrl
     *            ͼƬ��url·������http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(URL imageUrl) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ���ֽ�����Base64����
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// ����Base64��������ֽ������ַ���
    }

    /**
     * ������ͼƬ����Base64λ����
     * 
     * @param imageFile
     *            ͼƬ��url·������F:/.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ���ֽ�����Base64����
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// ����Base64��������ֽ������ַ���
    }

    /**
     * ��Base64λ�����ͼƬ���н��룬�����浽ָ��Ŀ¼
     * 
     * @param base64
     *            base64�����ͼƬ��Ϣ
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path,
            String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String [] args){
        URL url = null;
        try {
            url = new URL("http://p1s8k4lys.bkt.clouddn.com/pandas.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String encoderStr = Base64ImageUtils.encodeImgageToBase64(url);
        System.out.println(encoderStr);

        Base64ImageUtils.decodeBase64ToImage(encoderStr, "E:/", "pandas.jpg");
    }
}
