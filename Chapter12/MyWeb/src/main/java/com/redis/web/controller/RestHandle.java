package com.redis.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redis.web.base.RestClient;

@Controller
public class RestHandle {
	private Logger logger = LoggerFactory.getLogger(RestHandle.class);
	//nginx的请求转发地址
	private String restBaseUrl ="http://127.0.0.1:80";

	//请求的过滤地址，以/user/api开头的请求
	@RequestMapping(value={"/user/api/**"})
	public void handler(HttpServletRequest request,@RequestBody String json,HttpServletResponse response) throws ParseException, IOException {
		RestClient rest = new RestClient();
		String url = null;
		url =  request.getRequestURI();
//		url += "?" + "&clientip="+request.getRemoteAddr();
//		if (request.getQueryString() != null) {
//			url += "&" + request.getQueryString();
//		}

		String contextPath = request.getContextPath();
		int beginIdx = url.indexOf(contextPath) + contextPath.length();
		
		System.out.println("***111 url="+url + ", json="+json + ",contextPath="+contextPath);
		url = url.substring(beginIdx, url.length());
		System.out.println("***222 url="+url);
		
		//转发get请求
		if  (request.getMethod().toLowerCase().equals("get")){
			logger.debug("* method:get url:{}",restBaseUrl + url);
			 rest.get(restBaseUrl + url ,request,response);	
		}	
		
		//转发post请求
		if  (request.getMethod().toLowerCase().equals("post")){
			logger.debug("method:post url:{}  body:{}",restBaseUrl + url,json);
			rest.post(restBaseUrl + url,json ,request,response);
		}
		
		//转发put请求
		if  (request.getMethod().toLowerCase().equals("put")){
			
			logger.debug("method:put url:{}  body:{}",restBaseUrl + url,json);
			rest.put(restBaseUrl + url,json ,request,response);
		}
		
		//转发delete请求
		if  (request.getMethod().toLowerCase().equals("delete")){
			logger.debug("method:delete url:{}",restBaseUrl + url);
			rest.delete(restBaseUrl + url ,request,response);
		}
	}	
}