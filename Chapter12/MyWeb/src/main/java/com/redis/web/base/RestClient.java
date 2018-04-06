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
//	private HttpHost host;
	
//	public RestClient(String ip,int port) {
//		host = new HttpHost(ip, port, null);
//	}

	public HttpResponse getResponse(HttpUriRequest request){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		try {
			httpResponse = client.execute(request);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				return httpResponse;
			}
		} catch (Exception e) {
			client.getConnectionManager().shutdown();
			e.printStackTrace();
		}
		return httpResponse;
	}

	public String getResponseAsString(HttpUriRequest request){
		
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = client.execute(request);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}
	
	public void getResponseAsStream(HttpUriRequest request,HttpServletResponse response){
		
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = client.execute(request);
			HttpEntity entity = httpResponse.getEntity();
			Header[] headers = httpResponse.getAllHeaders();
			int statCode = httpResponse.getStatusLine().getStatusCode();
			response.setStatus(statCode);
			for(int i=0;i<headers.length;i++) {
				String key = headers[i].getName();
		        String value = headers[i].getValue();
				if(!"content-length".equals(key.toLowerCase())){
					if("content-type".equals(key.toLowerCase()) && value.indexOf("charset") == -1){
						value = value +";charset=utf-8";
						logger.info("reset Content-Type to add charset=utf-8");
					}
					
					logger.info("getResponseAsStream set header : {} = {}",key,value);
					response.setHeader(key, value);
				}
			}
			OutputStream os = response.getOutputStream();
			if (entity != null && entity.isStreaming()){
				InputStream is= entity.getContent();
				byte b[] = new byte[1024];  
                int j = 0;  
                while ((j = is.read(b)) != -1) {  
                    os.write(b, 0, j);  
                }  
                os.flush();  
                os.close(); 
                is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
	
	
	public String get(String url,String authToken){
		HttpGet get = new HttpGet(url);
		if (authToken != null) {
			get.setHeader("authToken", authToken);
		}
		return getResponseAsString(get);
	}
	
	public void get(String url,String authToken,HttpServletRequest request,HttpServletResponse response){
		HttpGet get = new HttpGet(url);
		if (authToken != null) {
			get.setHeader("authToken", authToken);
		}
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        if (!"content-length".equals(key.toLowerCase())){
	        	//logger.info("set header : {} = {}",key,value);
	        	get.setHeader(key, value);
	        }	
	    }
		getResponseAsStream(get,response);
	}
	

	public String delete(String url,String authToken){
		HttpDelete delete = new HttpDelete(url);
		if (authToken != null) {
			delete.setHeader("authToken", authToken);
		}
		return getResponseAsString(delete);
	}
	
	public void delete(String url,String authToken,HttpServletRequest request,HttpServletResponse response){
		HttpDelete delete = new HttpDelete(url);
		if (authToken != null) {
			delete.setHeader("authToken", authToken);
		}
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        if (!"content-length".equals(key.toLowerCase())){
	        	logger.info("set header : {} = {}",key,value);
	        	delete.setHeader(key, value);
	        }	
	    }
		getResponseAsStream(delete,response);
	}
	
	public String post(String url,String json,String authToken,HttpServletRequest request){
		HttpPost post = new HttpPost(url);
		if (json != null ) {
			post.setEntity(new StringEntity(json,ContentType.APPLICATION_JSON));
		}
		if (authToken != null) {
			post.setHeader("authToken", authToken);
		}
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        if (!"content-length".equals(key.toLowerCase())){
	        	logger.info("set header : {} = {}",key,value);
	        	post.setHeader(key, value);
	        }	
	    }
		return getResponseAsString(post);
		
	}
	
	public void post(String url,String json,String authToken
			,HttpServletRequest request,HttpServletResponse response){
		HttpPost post = new HttpPost(url);
		if (json != null ) {
			post.setEntity(new StringEntity(json,ContentType.APPLICATION_JSON));
		}
		if (authToken != null) {
			post.setHeader("authToken", authToken);
		}
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        if (!"content-length".equals(key.toLowerCase())){
	        	logger.info("set header : {} = {}",key,value);
	        	post.setHeader(key, value);
	        }	
	    }
		getResponseAsStream(post,response);
	}
	
	public void put(String url,String json,String authToken
			,HttpServletRequest request,HttpServletResponse response){
		HttpPut put = new HttpPut(url);
		
		if (json != null ) {
			put.setEntity(new StringEntity(json,ContentType.APPLICATION_JSON));
		}
		if (authToken != null) {
			put.setHeader("authToken", authToken);
		}
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        if (!"content-length".equals(key.toLowerCase())){
	        	logger.info("set header : {} = {}",key,value);
	        	put.setHeader(key, value);
	        }
	    }
	    getResponseAsStream(put,response);		
		
	}
	
	public String put(String url,String json,String authToken,HttpServletRequest request){
		HttpPut put = new HttpPut(url);
		
		if (json != null ) {
			put.setEntity(new StringEntity(json,ContentType.APPLICATION_JSON));
		}
		if (authToken != null) {
			put.setHeader("authToken", authToken);
		}
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        if (!"content-length".equals(key.toLowerCase())){
	        	logger.info("set header : {} = {}",key,value);
	        	put.setHeader(key, value);
	        }
	    }
		return getResponseAsString(put);		
		
	}

}
