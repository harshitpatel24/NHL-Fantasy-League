package com.nhlFantasy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhlFantasy.dao.UserRepository;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.LeagueService;
import com.fasterxml.jackson.databind.node.ObjectNode;;
@RestController
public class LeagueController {

	@Autowired
	LeagueService leagueService;
	UserRepository userRepository;
	
	@RequestMapping(value = "/api/addLeague", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody League addLeague(@RequestBody ObjectNode objNode,  HttpServletResponse response, HttpServletRequest  request) {

		//int id = 5;
		//User u = new User();
		//u.setUserid(5);
		//u.setUname("test");
		//u.setEmail("demo@gmail.com");
		//u.setPassword("test");
		//User u = userRepository.searchUserbyId(id);
		//league.setUser(u);
		//System.out.println(league.getUser());
		User u = new User();
		u.setUserid(Integer.parseInt(objNode.get("creatorId").get("userid").toString()));
		League l = new League();
		l.setLeagueCapacity(Integer.parseInt(objNode.get("leagueCapacity").toString()));
		l.setLeagueName(objNode.get("leagueName").toString());
		l.setLeaguePassword(objNode.get("leaguePassword").toString());
		l.setUser(u);
		//System.out.println(objNode.get("leaguePassword").toString());
	
		//return null;
		League l1 = leagueService.addLeague(l);
		
		return l1;
	}
}
