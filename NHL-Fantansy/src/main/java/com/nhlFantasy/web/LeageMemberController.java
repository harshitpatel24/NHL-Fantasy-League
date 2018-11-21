package com.nhlFantasy.web;

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
import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.User;
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
	
	//@Autowired
	//LeagueMember leagueMember;
	
//	@Autowired
//	League league;
//	
//	@Autowired
//	User user;
	
	boolean isMemberExists(int userId,int leagueId)
	{
		boolean exists;
		LeagueMember leagueMember =  null;
		leagueMember = leagueMemberService.findLeagueMemberbyUserid(leagueId, userId);
		
		if(leagueMember.getLeague().getLeagueId() == -1) {
			exists = false;
		} else {
			exists = true;
		}
		//System.out.println("exists = " + exists);
		return exists;
	}

	@RequestMapping(value = "/api/addLeagueMember", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode addLeagueMember(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request) {
	
		ObjectMapper mapper = new ObjectMapper();
		
		User user = null;
		League league = null;
		int leagueId = Integer.parseInt(objNode.get("leagueId").toString());
		String leaguePassword = objNode.get("leaguePassword").toString();
		int userid = Integer.parseInt(objNode.get("userid").toString());
		//System.out.println("league pass = " + leaguePassword + " league id = " + leagueId);
		
		leaguePassword = leaguePassword.substring(1, leaguePassword.length() -1);
		
		
		
		league = leagueService.findLeague(leagueId, leaguePassword);
		//count = leagueService.findLeague(leagueId);
		
		int numberofLeagueMembers = 0;
		int leagueCapacity = 0;
		
		LeagueMember leagueMemberObject = new LeagueMember();
		LeagueMember leagueMember = new LeagueMember();
			
		JsonNode node = null;
		//System.out.println("League is present in League table , count =" + league.getLeagueId());
		//System.out.println("League is present in League table , count =" + count);
		
		if (league.getLeagueId() != -1) {
			//System.out.println(" league poass = " + league.getLeaguePassword().toString() + " league pass input = " + leaguePassword);
			//if(league.getLeaguePassword().equals(leaguePassword)) {
				//System.out.println("into 000000");
				league = leagueService.findLeagueById(leagueId);
				
				//System.out.println("league id of league presenrt = " + league.getLeagueId());
				user = userService.searchUserbyuserId(userid);
				
				if(isMemberExists(user.getUserid(),league.getLeagueId()))
				{
					league.setLeagueId(-1);
					leagueMemberObject.setLeague(league);
					leagueMemberObject.setUser(user);
					node = mapper.convertValue(leagueMemberObject, JsonNode.class);
				}
				else
				{
					numberofLeagueMembers = leagueMemberService.countLeagueMember(league.getLeagueId());
					
					//System.out.println("number of league members in this league=" + numberofLeagueMembers);
					leagueCapacity = leagueService.findLeagueCapacity(league.getLeagueId());
					
					//System.out.println("league Capacity of league = " + leagueCapacity);
					
					if (leagueCapacity > numberofLeagueMembers) {
						
						//System.out.println("into 11111");
						leagueMember.setLeague(league);
						leagueMember.setUser(user);
						leagueMemberObject = leagueMemberService.addLeagueMember(leagueMember);
						node = mapper.convertValue(leagueMemberObject, JsonNode.class);
						
					} else {
						//System.out.println(league.getLeagueId());
						league.setLeagueId(-1);
						leagueMemberObject.setLeague(league);
						leagueMemberObject.setUser(user);
						node = mapper.convertValue(leagueMemberObject, JsonNode.class);
					}
				}
			//}
			
//			else {
//				node = mapper.convertValue(leagueObjForSave, JsonNode.class);
//				//return false;
//			}
		} else {
			System.out.println(league.getLeagueId());
			user = userService.searchUserbyuserId(userid);
			leagueMemberObject.setLeague(league);
			leagueMemberObject.setUser(user);
			node = mapper.convertValue(leagueMemberObject, JsonNode.class);
		}
		
		return node;
		
	}
}
