package com.nhlFantasy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.UserService;

@RestController
public class UserController 
{
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public String testMethod() {
		
		List<User> users = userService.getAllUsers();
		return "{ \"name\": \"bob\" }";

	}
}
