package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.User;

public interface UserService {

	//User findByUserId(Long id);
	List<User> getAllUsers();

	User addUser(User user);
	
	User getByUserId(User user);
	
	User updateUser(User user);
	
	User authenticateUser(String email, String password);
	
	boolean deleteUser(User user);
	
	User searchUserbyuserId(int id);
	
	User searchUserbyEmail(String email);
}
