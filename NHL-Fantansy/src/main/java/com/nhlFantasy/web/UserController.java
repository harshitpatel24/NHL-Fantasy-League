package com.nhlFantasy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User addUser(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
		User u = userService.addUser(user);
		return u;
	}
	
	@RequestMapping(value = "/api/user",  method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User getUserById(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
		User u = userService.getByUserId(user);
		return u;
	}
	
	@RequestMapping(value = "/api/update-user",  method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User updateUser(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
		User u  = userService.updateUser(user);
		
		return u; 
	}
	
	@RequestMapping(value = "/api/get-user", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User authenticateUser(@RequestBody User user,HttpServletResponse response, HttpServletRequest  request) {
		User u = userService.authenticateUser(user.getEmail(), user.getPassword());
		return u;
	}
	
	@RequestMapping(value = "/api/searchUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public User getUser(@PathVariable("id") int id) {
		User user = userService.searchUserbyuserId(id);
		
		return user;
	}
	
}
