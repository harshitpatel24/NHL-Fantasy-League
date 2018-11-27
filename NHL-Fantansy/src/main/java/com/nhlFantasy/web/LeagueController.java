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
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.LeagueMemberService;
import com.nhlFantasy.service.LeagueService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class LeagueController {

	@Autowired
	LeagueService leagueService;
	
	@Autowired
	LeagueMemberService leagueMemberService;
	
	@RequestMapping(value = "/api/addLeague", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode addLeague(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
		//System.out.println(objNode.toString());
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.setUserid(Integer.parseInt(objNode.get("creatorId").get("userid").toString()));
		League league = new League();
		String leagueCapacity  = objNode.get("leagueCapacity").toString();
		league.setLeagueCapacity(Integer.parseInt(leagueCapacity.substring(1, leagueCapacity.length()- 1)));
		
		String leagueName=objNode.get("leagueName").toString();
		league.setLeagueName(leagueName.substring(1,leagueName.length()-1));
		
		String leaguePassword = objNode.get("leaguePassword").toString();
		league.setLeaguePassword(leaguePassword.substring(1, leaguePassword.length()-1));
		league.setUser(user);
		
		League leagueObjForSave = leagueService.addLeague(league);
		JsonNode node = mapper.convertValue(leagueObjForSave, JsonNode.class);
		
		
		if (leagueObjForSave.getLeagueId() != -1)
		{
			LeagueMember leagueMember = new LeagueMember();
			leagueMember.setUser(user);
			leagueMember.setLeague(leagueObjForSave);
			leagueMember.setPoints(0);
			leagueMember.setBudget(8);
			LeagueMember leagueMemberObj = leagueMemberService.addLeagueMember(leagueMember);
		}
		return node;
	}
	
		@RequestMapping(value = "/api/getLeagueByLeagueId", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
		public @ResponseBody JsonNode getLeague(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
			ObjectMapper mapper = new ObjectMapper();
			League leagueObj = null;
			int leagueId = Integer.parseInt(objNode.get("leagueId").toString()); 
			leagueObj=leagueService.getLeagueByLeagueId(leagueId);
			JsonNode node = mapper.convertValue(leagueObj, JsonNode.class);
			return node;
		}
}