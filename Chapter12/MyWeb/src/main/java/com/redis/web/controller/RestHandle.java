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
	
	private String restBaseUrl ="http://127.0.0.1:80";

	@RequestMapping(value={"/user/api/**"})
	public void handler(HttpServletRequest request,@RequestBody String json,HttpServletResponse response) throws ParseException, IOException {

		RestClient rest = new RestClient();
		String url = null;
		String authToken = null;
		if (request.getSession() != null && request.getSession().getAttribute("authToken")!= null) {
			authToken = request.getSession().getAttribute("authToken").toString() ;
		}
		url =  request.getRequestURI();
		url += "?" + "&clientip="+request.getRemoteAddr();
		if (request.getQueryString() != null) {
			url += "&" + request.getQueryString();
		}
		logger.debug("authToken:{}",authToken);
		if  (request.getMethod().toLowerCase().equals("get")){
			logger.debug("method:get url:{}",restBaseUrl + url);
			 rest.get(restBaseUrl + url,authToken,request,response);	
		}	
		if  (request.getMethod().toLowerCase().equals("post")){
			logger.debug("method:post url:{}  body:{}",restBaseUrl + url,json);
			rest.post(restBaseUrl + url,json,authToken,request,response);
		}
		if  (request.getMethod().toLowerCase().equals("put")){
			
			logger.debug("method:put url:{}  body:{}",restBaseUrl + url,json);
			rest.put(restBaseUrl + url,json,authToken,request,response);
		}
		if  (request.getMethod().toLowerCase().equals("delete")){
			logger.debug("method:delete url:{}",restBaseUrl + url);
			rest.delete(restBaseUrl + url,authToken,request,response);
		}
		
	}	
}