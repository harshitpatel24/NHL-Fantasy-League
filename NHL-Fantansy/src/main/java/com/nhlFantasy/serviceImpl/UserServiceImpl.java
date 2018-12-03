package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.UserRepository;
import com.nhlFantasy.encryption.DESEncryptDecrypt;
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
		User u = null;
		try {
		String password = user.getPassword();
		DESEncryptDecrypt des = new DESEncryptDecrypt();
		String encryptedPass = des.encryptValue(password);
		user.setPassword(encryptedPass);
		u = userRepository.save(user); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override 
	public User updateUser(User user) {
		int flag = userRepository.updateUser(user.getUserid(), user.getUname(), user.getEmail(), user.getPassword()); 
		
		if (flag == 1)
		{
			 return this.getByUserId(user);  
		}
		else
		{
			User u = new User(); 
			u.setUserid(-1);
			return u;
		}
	}
	
	@Override
	public User getByUserId(User user) {
		User u = userRepository.getUserById(user.getUserid());
		return u;
	}

	@Override
	public User authenticateUser(String email, String password) {
		//User u = new User();
		User userObj = userRepository.searchUserbyEmail(email);
		String passwordFromDB = userObj.getPassword();
		
		String decryptedDBPass = "";
		try {
			DESEncryptDecrypt des = new DESEncryptDecrypt();
			decryptedDBPass = des.decryptValue(passwordFromDB);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("decryptedDBPass = " + decryptedDBPass + "and password = " + password);
		if (decryptedDBPass.equals(password)) {
			//System.out.println("into if");
			return userObj;
		} else {
			//System.out.println("into else");
			User blankUser = new User();
			blankUser.setUserid(-1);
			return blankUser;
		}
		//System.out.println("encrypted pas = "+ encryptedPass);
			//User u = userRepository.authenticateUser(encryptedPass, email);
			
//			if (u != null)
//			{
//				return u;
//			}
//			else
//			{
//				User blankUser = new User();
//				blankUser.setUserid(-1);
//				return blankUser;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		//return u;
		
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User searchUserbyuserId(int id) {
		// TODO Auto-generated method stub
		User u = userRepository.searchUserbyId(id);
		return u;
	}

	@Override
	public User searchUserbyEmail(String email) {
		// TODO Auto-generated method stub
		User u = userRepository.searchUserbyEmail(email);
		return u;
	}

}
