package com.example.user.controller;

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
	
	 * @ResponseBody
	 * @RequestBody User user
	 * 
	 * 
	 * 后端通过 @RequestBody 直接将json字符串绑定到对应的model上
	 */	
	@RequestMapping(value = "/v1/user/add", consumes = "application/json" ,method = RequestMethod.PUT )
	public  Map<String,Object> addUser(@RequestBody User user) {
		logger.info("--- PUT addUser() param=" );
		logger.info("*** user" + user);
		
		user.setId("user:"+ System.currentTimeMillis());
		
		
	//	userService.addUser(user);

	 //redisService.set(user.getId(), user);
		
		//redisService.addUser(user);
//		ValueOperations<String, User> operations=redisTemplate.opsForValue();
//	    operations.set("com.neox", user);
	        
		try{
			redisTemplate.opsForList().rightPush("user", user);
		}catch(Exception e){
			e.printStackTrace();
		}
	 	        
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", true);
		result.put("timestamp", System.currentTimeMillis());
		return result;
	}
	
	/**
	 * 查詢所有的用户
	 * 
	 */
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET)
	public Map<String,Object> queryUsers(){
		logger.info("**** queryUsers");
		
		//redisTemplate.opsForList().set("user", 2, "newTwo");
        List<User> list = redisTemplate.opsForList().range("user", 0, -1);
        
        Map<String,Object> rows = new HashMap<String,Object>();
        rows.put("total", list.size());
        rows.put("rows", list);
		return rows;
	}
	
	/**
	 * 刪除用户
	 * 
	 */
	@RequestMapping(value = "/v1/user/{userId}", method = RequestMethod.DELETE)
	public Map<String,Object> delUser(@PathVariable("userId") String userId){
		logger.info("**** delUser userId="+userId);
		
		List<User> list = redisTemplate.opsForList().range("user", 0, -1);
		Long removeCount = null;
		for(int i=0,j=list.size();i<j;i++){
			User user = list.get(i);
			logger.info(user);
			if( user.getId().equals(userId )){
				removeCount = redisTemplate.opsForList().remove("user", i, user);	
				logger.info("**** removeCount="+removeCount);
				
				break;
			}
		}

		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("removeCount", removeCount);
		result.put("timestamp", System.currentTimeMillis());
		return result;
	}
	
	/**
	 * 修改用户
	 * 
	 */
	@RequestMapping(value = "/v1/user/update", method = RequestMethod.POST)
	public Map<String,Object> updateUser(@RequestBody User user){		
		logger.info("**** updateUser user="+user);
		
		List<User> list = redisTemplate.opsForList().range("user", 0, -1);
		Long removeCount = null;
		for(int i=0,j=list.size();i<j;i++){
			User getUser = list.get(i);
//			user.setUserName("lisi2222");
//			logger.info(user);
			if( getUser.getId().equals(user.getId() )){
				redisTemplate.opsForList().set("user", i, user);	
				break;
			}
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", true);
		result.put("timestamp", System.currentTimeMillis());
		return result;
	}
	

}
