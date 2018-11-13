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
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public String registerUser(User user) {
		userService.addUser(user);
		
		User u = userService.getUser(user.getUname(),user.getEmail(),user.getPassword());
		
		String id = String.valueOf(u.getUserid());
		return "{ \"id\": \""+id+"\" }";
	}
	
//	@RequestMapping(value = "/api/get-user/{uname}/{email}/{password}", method = RequestMethod.GET)
//	public String getUser(@PathVariable String uname, @PathVariable String email, @PathVariable String password) {
//		//User u = userService.getUser(user.getUname(), user.getPassword(), user.getEmail());
//		
//		User u = userService.getUser(uname, email, password);
//		String id = String.valueOf(u.getUserid());
//		return "{ \"id\": \""+id+"\" }";
//	}
	
	@RequestMapping(value = "/api/get-user", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User getUser(@RequestBody User user,HttpServletResponse response,
			HttpServletRequest  request) {
		//User u = userService.getUser(user.getUname(), user.getPassword(), user.getEmail());
		
		User u = userService.getUser(user.getUname(), user.getEmail(), user.getPassword());
		
		//String id = String.valueOf(u.getUserid());
		return u;
		//return "{ \"id\": \""+id+"\" }";
	}
	
}
