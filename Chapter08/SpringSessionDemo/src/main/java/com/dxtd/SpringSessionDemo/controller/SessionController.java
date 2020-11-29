package com.dxtd.SpringSessionDemo.controller;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {

    /**
     * 登录系统
     *
     * http://127.0.0.1:8080/session/login?username=xinping&password=123
     * http://127.0.0.1:8081/session/login?username=xinping&password=123
     * */
    @RequestMapping(value="/session/login", method = RequestMethod.GET)
    @ResponseBody
    public Map login(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request, HttpSession session) {
        String message = "login failure";
        Map<String, Object> result = new HashMap<String, Object>();
        if(username != null && "xinping".equals(username) && "123".equals(password)){
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            request.getSession().setAttribute("admin", user);
            message = "login success";
            result.put("message" , message);
            result.put("sessionId",session.getId());
        }else{
            result.put("message" , message);
        }

        return result;
    }

    /**
     * 查询用户
     *
     * http://127.0.0.1:8080/session/get?username=xinping
     * http://127.0.0.1:8082/session/get?username=xinping
     * */
    @RequestMapping(value="/session/get", method = RequestMethod.GET)
    @ResponseBody
    public Map get(@RequestParam("username") String username,HttpServletRequest request, HttpSession session) {
        Object value = request.getSession().getAttribute("admin");
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("message" ,value);
        result.put("sessionId",session.getId());
        return result;
    }

    /**
     * 退出系统
     *
     * http://127.0.0.1:8080/session/logout
     * http://127.0.0.1:8081/session/logout
     * */
    @RequestMapping(value = "/session/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map logout(HttpSession session) {
        session.removeAttribute("admin");
        session.invalidate();
        Map result = new HashMap();
        result.put("message","退出系统");
        return result;
    }

}
