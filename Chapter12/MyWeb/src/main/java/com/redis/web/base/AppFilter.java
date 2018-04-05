package com.redis.web.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class AppFilter implements Filter {

    public AppFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    private String restBaseUrl = "http://127.0.0.1:80";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();

        String clientip = request.getRemoteAddr();
        String methodRequest = httpServletRequest.getMethod();
        String requestUrl = httpServletRequest.getRequestURL().toString();

        System.out.println("111----- AppFilter url=" + url + ",clientip=" + clientip + ",method=" + methodRequest
                + ",date=" + new Date() + ",\nrequestUrl=" + requestUrl + ",\ncontextPath=" + contextPath);

        Enumeration<?> enum1 = httpServletRequest.getParameterNames();
        Map params = httpServletRequest.getParameterMap();
        StringBuilder paramsTemp = new StringBuilder();

        if (enum1 != null) {
            while (enum1.hasMoreElements()) {
                String key = (String) enum1.nextElement();
                String value = ((String[]) params.get(key))[0];
                paramsTemp.append(key);
                paramsTemp.append("=");
                paramsTemp.append(value);
                paramsTemp.append("&");
            }
        }

        if (url.toLowerCase().indexOf("/user/api".toLowerCase()) > -1) {
            // 转换地址， 去掉请求项目名称
            int beginIdx = requestUrl.indexOf(contextPath) + contextPath.length();
            String realPath = restBaseUrl + requestUrl.substring(beginIdx, requestUrl.length());
        // String path = "http://127.0.0.1:80/ssh2/TestServlet.action" + "?"
            // + paramsTemp;
            String path = realPath;
            System.out.println("*** 222 path="+path + "\nmethodRequest=" +methodRequest);

            if (methodRequest.toLowerCase().equals("get")) {
              
                HttpClient client = new HttpClient();
                GetMethod method = new GetMethod(path);
                method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

                try {
                    client.executeMethod(method);
                    InputStream is = method.getResponseBodyAsStream();
                    OutputStream os = response.getOutputStream();
                    byte b[] = new byte[1024];
                    int j = 0;
                    while ((j = is.read(b)) != -1) {
                        os.write(b, 0, j);
                    }
                    os.flush();
                    os.close();
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (methodRequest.toLowerCase().equals("post")) {
               
                HttpClient client = new HttpClient();
                PostMethod method = new PostMethod(path);
                method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

                Enumeration headerNames = httpServletRequest.getHeaderNames();
        	    while (headerNames.hasMoreElements()) {
        	        String key = (String) headerNames.nextElement();
        	        String value = httpServletRequest.getHeader(key);
        	        if (!"content-length".equals(key.toLowerCase())){
        	        	method.setRequestHeader(key, value);
        	        }	
        	    }
        	    
                try {
                    client.executeMethod(method);
                    InputStream is = method.getResponseBodyAsStream();
                    OutputStream os = response.getOutputStream();
                    byte b[] = new byte[1024];
                    int j = 0;
                    while ((j = is.read(b)) != -1) {
                        os.write(b, 0, j);
                    }
                    os.flush();
                    os.close();
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (methodRequest.toLowerCase().equals("delete")) {
               
                HttpClient client = new HttpClient();
                DeleteMethod  method = new DeleteMethod (path);
                method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

                try {
                    client.executeMethod(method);
                    InputStream is = method.getResponseBodyAsStream();
                    OutputStream os = response.getOutputStream();
                    byte b[] = new byte[1024];
                    int j = 0;
                    while ((j = is.read(b)) != -1) {
                        os.write(b, 0, j);
                    }
                    os.flush();
                    os.close();
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }

        
    }

}

