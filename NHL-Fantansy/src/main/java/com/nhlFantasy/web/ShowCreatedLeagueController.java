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
import com.nhlFantasy.service.ShowCreatedLeagueService;

@RestController
public class ShowCreatedLeagueController {
	
	@Autowired
	ShowCreatedLeagueService showCreatedLeagueService;

	@RequestMapping(value = "/api/showcreatedleague", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode showCreatedLeague(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request,HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		ArrayList<League> createdLeagues = new ArrayList<League>();
		
		if(session.getAttribute("userid") == null)
		{
			node = mapper.convertValue(createdLeagues, JsonNode.class);
		}
		else
		{
			String createrIdStr = objNode.get("creatorId").toString();
			int creatorId = Integer.parseInt(createrIdStr.substring(1,createrIdStr.length()-1));
			if(Integer.parseInt(session.getAttribute("userid").toString()) == creatorId)
			{
				createdLeagues = showCreatedLeagueService.getCreatedLeaguesService(creatorId);
				node = mapper.convertValue(createdLeagues, JsonNode.class);
			}
			else
			{
				node = mapper.convertValue(createdLeagues, JsonNode.class);
			}
		}
		return node;
	}
}
