package com.nhlFantasy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public @ResponseBody JsonNode addLeague(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request,HttpSession session) {
		//System.out.println(objNode.toString());
		ObjectMapper mapper = new ObjectMapper();
		League league = null;
		User user = null;
		JsonNode node = null;
		
		if(session.getAttribute("userid") == null)
		{
			league = new League();
			user = new User();
			user.setUserid(-1);
			league.setLeagueId(-1);
			league.setUser(user);
			node = mapper.convertValue(league, JsonNode.class);
		}
		else
		{
			String userIdStr=objNode.get("creatorId").get("userid").toString();
			int userId = Integer.parseInt(userIdStr);
			if(Integer.parseInt(session.getAttribute("userid").toString()) == userId)
			{
				league = new League();
				user = new User();
				user.setUserid(userId);
				String leagueCapacity  = objNode.get("leagueCapacity").toString();
				league.setLeagueCapacity(Integer.parseInt(leagueCapacity.substring(1, leagueCapacity.length()- 1)));
				
				String leagueName=objNode.get("leagueName").toString();
				league.setLeagueName(leagueName.substring(1,leagueName.length()-1));
				
				String leaguePassword = objNode.get("leaguePassword").toString();
				league.setLeaguePassword(leaguePassword.substring(1, leaguePassword.length()-1));
				league.setUser(user);
				
				League leagueObjForSave = leagueService.addLeague(league);
				node = mapper.convertValue(leagueObjForSave, JsonNode.class);
				
				
				if (leagueObjForSave.getLeagueId() != -1)
				{
					LeagueMember leagueMember = new LeagueMember();
					leagueMember.setUser(user);
					leagueMember.setLeague(leagueObjForSave);
					
					LeagueMember leagueMemberObj = leagueMemberService.addLeagueMember(leagueMember);
				}
			}
			else
			{
				league = new League();
				user = new User();
				user.setUserid(-1);
				league.setLeagueId(-1);
				league.setUser(user);
				node = mapper.convertValue(league, JsonNode.class);			}
		}
		
		//User user = new User();
		//user.setUserid(Integer.parseInt(objNode.get("creatorId").get("userid").toString()));
		//League league = new League();
		
		
		
		return node;
	}
}
