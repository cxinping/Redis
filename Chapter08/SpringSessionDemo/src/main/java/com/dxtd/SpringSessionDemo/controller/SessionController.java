package com.dxtd.SpringSessionDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SessionController {
    // http://127.0.0.1:8082/session?username=xinping
    @RequestMapping("/session")
    public Object springSession(@RequestParam("username") String username, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains("JSESSION")) {
                    System.out.println(cookie.getName() + "=" + cookie.getValue());
                }
            }
        }
        Object value = session.getAttribute("username");
        if (value == null) {
            System.out.println("用户不存在");
            session.setAttribute("username", "{username: '" + username+ "', age: 28}");
        } else {
            System.out.println("用户存在");
        }
        return "username=" + value;
    }


}
