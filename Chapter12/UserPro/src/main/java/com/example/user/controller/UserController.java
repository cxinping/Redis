package com.example.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.domain.User;
import com.example.user.service.IUserService;
import com.example.user.service.RedisService;

@RestController
@RequestMapping("/users")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	
	private IUserService userService;

	@Autowired
	private RedisService redisService;
	
	 @Autowired
	 private RedisTemplate redisTemplate;
	 
	@Autowired
	public UserController(IUserService userService){
		this.userService = userService;
	}
	
	/**
	 * 添加用户
	 * 
	 */
	@RequestMapping(value = "/v1/user/add", method = RequestMethod.POST)
	public Map addUser(@RequestBody User user) {
		System.out.println("--- POST addUser() redisService=" + redisService);
		user.setId("user_"+ System.currentTimeMillis());
		logger.info(user);
	//	userService.addUser(user);

	 //redisService.set(user.getId(), user);
		
		//redisService.addUser(user);
//		ValueOperations<String, User> operations=redisTemplate.opsForValue();
//	    operations.set("com.neox", user);
	        
	 	redisTemplate.opsForList().rightPush("user", user);
	        
		Map result = new HashMap<>();
		result.put("result", true);
		result.put("timestamp", System.currentTimeMillis());
		return result;
	}
	
	/**
	 * 查詢所有的用户
	 * 
	 */
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET)
	public List queryUser(){
		logger.info("**** queryUser");
		
		//redisTemplate.opsForList().set("user", 2, "newTwo");
        List<User> list4 = redisTemplate.opsForList().range("user", 0, -1);
        
		return list4;
	}
	
	/**
	 * 刪除用户
	 * 
	 */
	@RequestMapping(value = "/v1/user/{userId}", method = RequestMethod.DELETE)
	public Map delUser(@PathVariable("userId") String userId){
		logger.info("**** delUser userId="+userId);
		
		List<User> list = redisTemplate.opsForList().range("user", 0, -1);
		Long removeCount = null;
		for(int i=0,j=list.size();i<j;i++){
			User user = list.get(i);
			logger.info(user);
			if( user.getId().equals(userId )){
				removeCount = redisTemplate.opsForList().remove("user", i, user);	
				break;
			}
		}
		

		Map result = new HashMap<>();
		result.put("result", true);
		result.put("removeCount", removeCount);
		result.put("timestamp", System.currentTimeMillis());
		return result;
		
	}
	
	/**
	 * 修改用户
	 * 
	 */
	@RequestMapping(value = "/v1/user/{userId}", method = RequestMethod.POST)
	public Map updateUser(@PathVariable("userId") String userId){
		
		logger.info("**** updateUser");
		
		List<User> list = redisTemplate.opsForList().range("user", 0, -1);
		Long removeCount = null;
		for(int i=0,j=list.size();i<j;i++){
			User user = list.get(i);
			user.setUserName("lisi");
			logger.info(user);
			if( user.getId().equals(userId )){
				redisTemplate.opsForList().set("user", i, user);	
				break;
			}
		}
		
		Map result = new HashMap<>();
		result.put("result", true);
		result.put("timestamp", System.currentTimeMillis());
		return result;
	}
	

	@RequestMapping("/queryUser")
	public Map getUser() {
		Map user = new HashMap();
		user.put("name", "wangwu");
		user.put("age", 20);
		user.put("timestamp", System.currentTimeMillis());
		return user;
	}

	@RequestMapping("/queryUsers")
	public List getUsers() {
		List list = new ArrayList();
		Map user1 = new HashMap();
		user1.put("name", "wangwu");
		user1.put("age", 20);

		Map user2 = new HashMap();
		user2.put("name", "lisi");
		user2.put("age", 25);

		list.add(user1);
		list.add(user2);
		return list;
	}

}
