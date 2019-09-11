package com.example.user.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.user.domain.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	public void addUser(User user) {
		System.out.println("*** addUser user="+user );

	}

}
