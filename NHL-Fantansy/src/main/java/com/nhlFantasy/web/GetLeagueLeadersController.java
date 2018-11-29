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
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.service.GetLeagueLeadersService;

@RestController
public class GetLeagueLeadersController {
	@Autowired
	GetLeagueLeadersService getLeagueLeadersService;
	
	@RequestMapping(value = "/api/getLeagueLeaders", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode getLeagueLeaders(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		String leagueIdStr = objNode.get("leagueId").toString();
		//leagueIdStr = leagueIdStr.substring(1, leagueIdStr.length()- 1); mohit
		int leagueId = Integer.parseInt(leagueIdStr);
		
		List<LeagueMember> leagueMembers = getLeagueLeadersService.getLeagueLeaders(leagueId);
		
		//System.out.println("list of hockey players = " + hockeyPlayers.size());
		JsonNode node = null;
		
		node = mapper.convertValue(leagueMembers, JsonNode.class);
		
		return node;
		
	}
}
