package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.User;

public interface UserService {

	//User findByUserId(Long id);
	List<User> getAllUsers();
	
	User addUser(User user);
	
	User authenticateUser(String email,String password);
	
	boolean deleteUser(User user);
	
	
}
