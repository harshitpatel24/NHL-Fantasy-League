package com.nhlFantasy.web;

import java.util.ArrayList;
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
import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.MemberTeam;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.HockeyPlayerService;
import com.nhlFantasy.service.LeagueMemberService;
import com.nhlFantasy.service.MemberTeamService;



@RestController
public class MemberTeamController {

	@Autowired
	MemberTeamService memberTeamService;
	
	@Autowired
	LeagueMemberService leagueMemberService;
	
	@Autowired
	HockeyPlayerService hockeyPlayerService;	
	
	@RequestMapping(value = "/api/addSelectedPlayersByMember", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode addLeagueMember(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		
		String leagueIdStr = objNode.get("leagueId").toString();
		int leagueId = Integer.parseInt(leagueIdStr);
		
		String userIdStr = objNode.get("userid").toString();
		int userid = Integer.parseInt(userIdStr);
		
		LeagueMember leagueMember = leagueMemberService.findLeagueMemberbyUserid(leagueId, userid);
		if(leagueMember == null)
		{
			leagueMember = new LeagueMember();
			leagueMember.setId(-1);
		}
		else
		{
			ArrayList<Integer> playerIds = new ArrayList<Integer>();
			String playerIdsStr = objNode.get("playerIds").toString();
			String player = "";
			for(int i =0; i < playerIdsStr.length(); i++)
			{
				if(playerIdsStr.charAt(i) == '[')
				{
					
				}
				else if(playerIdsStr.charAt(i) == ']')
				{
					playerIds.add(Integer.parseInt(player));
					player = "";
				}
				else if(playerIdsStr.charAt(i) == ',')
				{
					playerIds.add(Integer.parseInt(player));
					player = "";
				}
				else
				{
					player = player + playerIdsStr.charAt(i);
				}
			}
			memberTeamService.removeSelectedPlayers(leagueMember.getId());
			for(int i = 0; i < playerIds.size(); i++ )
			{
				MemberTeam memberTeam = new MemberTeam();
				HockeyPlayer hockeyPlayer = new HockeyPlayer();
				hockeyPlayer.setPlayerId(playerIds.get(i));
				memberTeam.setHockeyPlayer(hockeyPlayer);
				memberTeam.setLeagueMember(leagueMember);
				MemberTeam memberTeamObj = memberTeamService.addMemberTeam(memberTeam);
			}
				
		}
		//LeagueMember leagueMemberObject = new LeagueMember(); 
		JsonNode node = null;
		node = mapper.convertValue(leagueMember, JsonNode.class);
		return node;
	}
	
	@RequestMapping(value = "/api/getSelectedPlayersByMembers", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode getSelectedPlayersByMembers(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		String memberIdStr = objNode.get("memberId").toString();
		int memberId = Integer.parseInt(memberIdStr);
		
		List<MemberTeam> hockeyPlayers = memberTeamService.getSelectedPlayersByMembers(memberId);
		
		//System.out.println("list of hockey players = " + hockeyPlayers.size());
		JsonNode node = null;
		
		node = mapper.convertValue(hockeyPlayers, JsonNode.class);
		
		return node;
	}
	
}
