package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.User;

public interface UserService {

	//User findByUserId(Long id);
	List<User> getAllUsers();
}
