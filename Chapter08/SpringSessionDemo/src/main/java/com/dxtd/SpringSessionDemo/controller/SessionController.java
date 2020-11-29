package com.dxtd.SpringSessionDemo.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {

    // http://127.0.0.1:8080/session/login?username=xinping&password=123
    @RequestMapping("/session/login")
    @ResponseBody
    public Map login(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request, HttpSession session) {
        System.out.println("===============登录成功===============");
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        request.getSession().setAttribute("admin", user);
        String sessionId = session.getId();
        System.out.println("sessionId="+sessionId);
        Map result = new HashMap();
        result.put("message" , user);
        result.put("sessionId",sessionId);
        return result;
    }

    // http://127.0.0.1:8080/session/get?username=xinping
    @RequestMapping("/session/get")
    @ResponseBody
    public Map get(@RequestParam("username") String username,HttpServletRequest request, HttpSession session) {
        System.out.println("=============== 查询用户信息 ===============");
        Object value = request.getSession().getAttribute("admin");
        Map result = new HashMap();
        String sessionId = session.getId();
        result.put("message" ,value);
        result.put("sessionId",sessionId);
        return result;
    }

    // http://127.0.0.1:8080/session/logout
    @PostMapping(value = "logout")
    @ResponseBody
    public Map logout(HttpSession session) {
        System.out.println("============退出系统=============");
        System.out.println(session.getId());
        session.removeAttribute("admin");
        session.invalidate();
        Map result = new HashMap();
        result.put("message","退出系统");
        return result;
    }




}
