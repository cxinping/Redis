package com.redis.web.base;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.web.controller.RestHandle;

public class RestClient {
	private Logger logger = LoggerFactory.getLogger(RestHandle.class);


	/**
	 * 得到请求的响应值，并以输出流的形式进行处理
	 * 
	 * */
	public void getResponseAsStream(HttpUriRequest request, HttpServletResponse response) {
		//默认的http客户
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = client.execute(request);
			HttpEntity entity = httpResponse.getEntity();
			Header[] headers = httpResponse.getAllHeaders();
			int statCode = httpResponse.getStatusLine().getStatusCode();
			response.setStatus(statCode);
			for (int i = 0; i < headers.length; i++) {
				String key = headers[i].getName();
				String value = headers[i].getValue();
				if (!"content-length".equals(key.toLowerCase())) {
					if ("content-type".equals(key.toLowerCase()) && value.indexOf("charset") == -1) {
						value = value + ";charset=utf-8";
						logger.info("reset Content-Type to add charset=utf-8");
					}
					logger.info("getResponseAsStream set header : {} = {}", key, value);
					//设置响应头的信息
					response.setHeader(key, value);
				}
			}
			//得到请求的输出流
			OutputStream os = response.getOutputStream();
			if (entity != null && entity.isStreaming()) {
				InputStream is = entity.getContent();
				//设置缓存大小为 1024个字节
				byte b[] = new byte[1024];
				int j = 0;
				while ((j = is.read(b)) != -1) {
					os.write(b, 0, j);
				}
				//刷新输出流
				os.flush();
				//关闭输出流
				os.close();
				//关闭输入流
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭客户端连接
			client.getConnectionManager().shutdown();
		}
	}

	/**
	 * 执行http的get请求
	 * 
	 * */
	public void get(String url, HttpServletRequest request, HttpServletResponse response) {
		// 设置get请求
		HttpGet get = new HttpGet(url);
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if (!"content-length".equals(key.toLowerCase())) {
				logger.info("set header : {} = {}", key, value);
				//设置请求头的信息
				get.setHeader(key, value);
			}
		}
		getResponseAsStream(get, response);
	}

	/**
	 * 执行http的delete请求
	 * 
	 * */
	public void delete(String url, HttpServletRequest request, HttpServletResponse response) {
		// 设置delete请求
		HttpDelete delete = new HttpDelete(url);
		//得到请求头参数
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if (!"content-length".equals(key.toLowerCase())) {
				logger.info("set header : {} = {}", key, value);
				delete.setHeader(key, value);
			}
		}
		getResponseAsStream(delete, response);
	}

	/**
	 * 执行http的post请求
	 * 
	 * */
	public void post(String url, String json, HttpServletRequest request, HttpServletResponse response) {
		// 设置post请求
		HttpPost post = new HttpPost(url);
		if (json != null) {
			post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		}
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if (!"content-length".equals(key.toLowerCase())) {
				logger.info("set header : {} = {}", key, value);
				post.setHeader(key, value);
			}
		}
		getResponseAsStream(post, response);
	}


	/**
	 * 执行http的put请求
	 * 
	 * */
	public void put(String url, String json, HttpServletRequest request, HttpServletResponse response) {
		// 设置put请求
		HttpPut put = new HttpPut(url);

		if (json != null) {
			put.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		}
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if (!"content-length".equals(key.toLowerCase())) {
				logger.info("set header : {} = {}", key, value);
				put.setHeader(key, value);
			}
		}
		getResponseAsStream(put, response);
	}

}
