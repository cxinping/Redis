package com.redis.util;

import java.text.DecimalFormat;

public class Tools {

    public static String setMBSize(int size) {  
        //��ȡ����sizeΪ��1705230  
        int MB = 1024 * 1024;//����MB�ļ��㳣��  
        DecimalFormat df = new DecimalFormat("0.00");//��ʽ��С��  
        String resultSize = "";  
        resultSize = df.format(size / (float) MB) ;
        return resultSize;
    }  
    
	public static void main(String[] args) {
		System.out.println( Tools.setMBSize(731520));

	}

}