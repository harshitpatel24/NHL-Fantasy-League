package com.nhlFantasy.serviceImpl;

import java.util.ArrayList;
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

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		String email = user.getEmail();
		String uname = user.getUname();
		String password = user.getPassword();
		
		//userRepository.addUser(uname, email, password);
		
		return null;
	}

	@Override
	public User getUser(String username, String email, String password) {
		// TODO Auto-generated method stub
		
		User u = userRepository.getUser(username,password,email);
		return u;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
