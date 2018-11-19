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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class LeagueController {

	@Autowired
	LeagueService leagueService;
	UserRepository userRepository;
	
	@RequestMapping(value = "/api/addLeague", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode addLeague(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
		
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.setUserid(Integer.parseInt(objNode.get("creatorId").get("userid").toString()));
		League league = new League();
		league.setLeagueCapacity(Integer.parseInt(objNode.get("leagueCapacity").toString()));
		league.setLeagueName(objNode.get("leagueName").toString());
		league.setLeaguePassword(objNode.get("leaguePassword").toString());
		league.setUser(user);
		
		League leagueObjForSave = leagueService.addLeague(league);
		JsonNode node = mapper.convertValue(leagueObjForSave, JsonNode.class);
		
		
		return node;
	}
}
