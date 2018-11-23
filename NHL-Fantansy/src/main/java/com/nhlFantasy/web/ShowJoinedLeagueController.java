package com.nhlFantasy.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.service.ShowJoinedLeagueService;

@RestController
public class ShowJoinedLeagueController {
	
	@Autowired
	ShowJoinedLeagueService showJoinedLeagueService;
	
	@RequestMapping(value = "/api/showjoinedleague", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode showJoinedLeague(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request,HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		ArrayList<League> joinedLeagues = new ArrayList<League>();
		if(session.getAttribute("userid") == null)
		{
			node = mapper.convertValue(joinedLeagues, JsonNode.class);
		}
		else
		{
			String userIdStr = objNode.get("userId").toString();
			int userId = Integer.parseInt(userIdStr.substring(1,userIdStr.length()-1));
			if(Integer.parseInt(session.getAttribute("userid").toString()) == userId)
			{
				joinedLeagues = showJoinedLeagueService.getCreatedLeaguesService(userId);
				node = mapper.convertValue(joinedLeagues, JsonNode.class);
			}
			else
			{
				node = mapper.convertValue(joinedLeagues, JsonNode.class);
			}
		}
		return node;
	}
}
