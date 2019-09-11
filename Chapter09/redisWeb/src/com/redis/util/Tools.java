package com.redis.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

	public static String getCurrntTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		return format.format(now);
	}

	public static String transByteToMBSize(int size) {
		int MB = 1024 * 1024;// ����MB�ļ��㳣��
		DecimalFormat df = new DecimalFormat("0.00");// ��ʽ��С��
		String resultSize = "";
		resultSize = df.format(size / (float) MB);
		return resultSize;
	}

	public static void main(String[] args) {
		System.out.println(Tools.transByteToMBSize(731520));

	}

}
