package com.maddy.user.service;

import java.util.List;

import com.maddy.user.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);
}
