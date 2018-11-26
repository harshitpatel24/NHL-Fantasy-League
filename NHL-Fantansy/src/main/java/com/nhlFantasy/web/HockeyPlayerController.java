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
import com.nhlFantasy.entity.HockeyPlayer;
import com.nhlFantasy.service.HockeyPlayerService;

@RestController
public class HockeyPlayerController {
	
	@Autowired
	HockeyPlayerService hockeyPlayerService;

	@RequestMapping(value = "/api/getAllPlayersbyTeam", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode getAllHockeyPlayersByTeam(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		
		String teamName = objNode.get("teamName").toString();
		teamName = teamName.substring(1, teamName.length()- 1);
		System.out.println("team name = "+ teamName);
		
		List<HockeyPlayer> hockeyPlayers = hockeyPlayerService.findAllHockeyPlayerByTeam(teamName);
		
		//System.out.println("list of hockey players = " + hockeyPlayers.size());
		JsonNode node = null;
		
		node = mapper.convertValue(hockeyPlayers, JsonNode.class);
		
		return node;
	}
	
	@RequestMapping(value = "/api/getTeams", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode getTeams(HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> teams = hockeyPlayerService.getTeams();
		
		for (String team : teams) {
			System.out.println(team);
		}
		
		//System.out.println("list of hockey players = " + hockeyPlayers.size());
		JsonNode node = null;
		
		node = mapper.convertValue(teams, JsonNode.class);
		
		return node;
	}
	
}
