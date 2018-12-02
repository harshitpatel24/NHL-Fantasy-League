package com.nhlFantasy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhlFantasy.entity.HockeyPlayerStatsArchive;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.HockeyPlayerStatsArchiveService;
import com.nhlFantasy.service.LeagueMemberService;
import com.nhlFantasy.service.LeagueService;
import com.nhlFantasy.service.UserService;

@RestController
public class LeageMemberController {
	
	@Autowired
	LeagueService leagueService;
	
	@Autowired
	LeagueMemberService leagueMemberService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HockeyPlayerStatsArchiveService hockeyPlayerStatsArchiveService;

	@RequestMapping(value = "/api/addLeagueMember", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode addLeagueMember(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		
		String tempLeagueId = objNode.get("leagueId").toString();
		int leagueId = Integer.parseInt(tempLeagueId.substring(1, tempLeagueId.length()-1));
		
		String tempUserId = objNode.get("userid").toString();
		int userid = Integer.parseInt(tempUserId);
		
		String leaguePassword = objNode.get("leaguePassword").toString();
		leaguePassword = leaguePassword.substring(1, leaguePassword.length() -1);
		
		
		LeagueMember leagueMemberObject = new LeagueMember(); 
		JsonNode node = null;
		
		League league = leagueService.findLeague(leagueId, leaguePassword);
		
		if (league.getLeagueId() == -1)
		{
			leagueMemberObject.setId(-1);
			node = mapper.convertValue(leagueMemberObject, JsonNode.class);
			return node; 	
		}
		
		LeagueMember tempLeagueMember = leagueMemberService.findLeagueMemberbyUserid(leagueId, userid);
		
		if (tempLeagueMember != null)
		{
			leagueMemberObject.setId(-1);
			node = mapper.convertValue(leagueMemberObject, JsonNode.class);
			return node;
		}
		
		int numberofLeagueMembers = leagueMemberService.countLeagueMember(league.getLeagueId());
		
		if (numberofLeagueMembers + 1 <= league.getLeagueCapacity())
		{
			LeagueMember leagueMember = new LeagueMember();
			User user = new User();
			user.setUserid(userid);
			leagueMember.setUser(user);
			League tempLeague = new League();
			tempLeague.setLeagueId(leagueId);
			leagueMember.setLeague(tempLeague);
			leagueMember.setPoints(0);
			leagueMember.setBudget(80);
			leagueMemberObject = leagueMemberService.addLeagueMember(leagueMember);	
			node = mapper.convertValue(leagueMemberObject, JsonNode.class);
			return node; 
		}
		else 
		{
			leagueMemberObject.setId(-1);
			node = mapper.convertValue(leagueMemberObject, JsonNode.class);
			return node;
		}
	}
	
	@RequestMapping(value = "/api/getPointsHistory", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode getPointHistory(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
		ObjectMapper mapper = new ObjectMapper();
		
		String tempLeagueId = objNode.get("leagueId").toString();
		int leagueId = Integer.parseInt(tempLeagueId);
		
		String tempUserId = objNode.get("userid").toString();
		int userid = Integer.parseInt(tempUserId);
		List<HockeyPlayerStatsArchive> hockeyPlayerStatsArchive = hockeyPlayerStatsArchiveService.getPointHistory(leagueId,userid);
		JsonNode node = null;
		node = mapper.convertValue(hockeyPlayerStatsArchive, JsonNode.class);
		return node;
	}
}
