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

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		String email = user.getEmail();
		String uname = user.getUsername();
		String password = user.getPassword();
		
		//userRepository.addUser(uname, email, password);
		
		return null;
	}

	@Override
	public User authenticateUser(String email, String password) {
		
		User u = userRepository.authenticateUser(password, email);
		
		if (u != null)
		{
			return u;
		}
		else
		{
			User blankUser = new User();
			blankUser.setUserid(-1);
			return blankUser;
		}
		
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
