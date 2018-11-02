package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.UserRepository;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	/*
	@Override
	public User findByUserId(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(id);
	}
	*/
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.getAllUsers();
	}

}
