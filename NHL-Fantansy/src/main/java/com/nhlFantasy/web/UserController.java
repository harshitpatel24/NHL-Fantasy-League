package com.nhlFantasy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	User getDummyUser()
	{
		User user = new User();
		user.setUserid(-1);
		return user;
	}
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User addUser(@RequestBody User user, HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		User userObj = null;
		if(session.getAttribute("userid") == null)
		{
			userObj = userService.addUser(user);
		}
		else
		{
			userObj = getDummyUser();
		}
		return userObj;
	}
	
	@RequestMapping(value = "/api/user",  method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User getUserById(@RequestBody User user, HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		User userObj = null;
		if(Integer.parseInt(session.getAttribute("userid").toString()) == user.getUserid())
		{
			userObj = userService.getByUserId(user);
		}
		else
		{
			userObj = getDummyUser();
		}
		return userObj;
		
	}
	
	@RequestMapping(value = "/api/update-user",  method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User updateUser(@RequestBody User user, HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		User userObj = null;
		if(Integer.parseInt(session.getAttribute("userid").toString()) == user.getUserid())
		{
			userObj  = userService.updateUser(user);
		}
		else
		{
			userObj = getDummyUser();
		}
		return userObj; 
	}
	
	@RequestMapping(value = "/api/get-user", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody User authenticateUser(@RequestBody User user,HttpServletResponse response, HttpServletRequest  request, HttpSession session) {
		User userObj = null;
		System.out.println(session.getAttribute("userid"));
		if(session.getAttribute("userid") == null)
		{
			userObj = userService.authenticateUser(user.getEmail(), user.getPassword());
			session.setAttribute("userid", userObj.getUserid());
			
		}
		else
		{
			userObj = getDummyUser();
		}
		//System.out.println("\n\n\n");
		//System.out.println(u.getUname());
		//String uid = UUID.randomUUID().toString();
		//SessionUtil.setLoginSession(request, uid);
		//System.out.println(session.getAttribute("username"));
        
//		SessionUtil.setLoginSession(request, "");
//		User userSession = SessionUtil.getUserSession(request);
//		System.out.println("usersession=" + userSession.getEmail());
//		
		//CookieUtil cookieUtil = new CookieUtil();
		//String cookieData = autheToken;
		//response.addCookie(cookieUtil.setCookieData("_ttData", cookieData));
		return userObj;
	}
	
	@RequestMapping(value = "/api/searchUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public User getUser(@PathVariable("id") int id,HttpSession session) {
		User userObj = null;
		if(Integer.parseInt(session.getAttribute("userid").toString()) == id)
		{
			userObj = userService.searchUserbyuserId(id);
		}
		else
		{
			userObj = getDummyUser();
		}
		return userObj;
	}
	
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET,consumes = "application/json")
	public @ResponseBody User logout(HttpSession session,HttpServletResponse response, HttpServletRequest  request) {
		//System.out.println("session = " + session.getAttribute("username"));
		if(session.getAttribute("userid") != null)
			session.removeAttribute("userid");
		
		User userObj = getDummyUser();
		return userObj;
	}
	
}
