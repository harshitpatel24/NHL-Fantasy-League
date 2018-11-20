package com.nhlFantasy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.LeagueMemberService;
import com.nhlFantasy.service.LeagueService;
import com.nhlFantasy.service.UserService;

public class LeageMemberController {
	
	@Autowired
	LeagueService leagueService;
	
	@Autowired
	LeagueMemberService leagueMemberService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LeagueMember leagueMember;
	
	@Autowired
	League league;
	
	@Autowired
	User user;

	@RequestMapping(value = "/api/addLeagueMember", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode addLeagueMember(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		
		int leagueId = Integer.parseInt(objNode.get("leagueId").toString());
		String leaguePassword = objNode.get("leaguePassword").toString();
		int userid = Integer.parseInt(objNode.get("userid").toString());
		
		int count = 0;
		
		count = leagueService.searchLeague(leagueId, leaguePassword);
		
		int numberofLeagueMembers = 0;
		int leagueCapacity = 0;
		
		LeagueMember leagueMemberObject = new LeagueMember();
		JsonNode node = null;
		if (count > 0) {
			league = leagueService.findLeagueById(leagueId);
			
			numberofLeagueMembers = leagueMemberService.countLeagueMember(league.getLeagueId());
			
			leagueCapacity = leagueService.findLeagueCapacity(league.getLeagueId());
			
			user = userService.searchUserbyuserId(userid);
			
			
			if (leagueCapacity > numberofLeagueMembers) {
				
				leagueMember.setLeague(league);
				leagueMember.setUser(user);
				leagueMemberObject = leagueMemberService.addLeagueMember(leagueMember);
				node = mapper.convertValue(leagueMemberObject, JsonNode.class);
				
			} 
//			else {
//				node = mapper.convertValue(leagueObjForSave, JsonNode.class);
//				//return false;
//			}
		}
		
		return node;
		
	}
}
