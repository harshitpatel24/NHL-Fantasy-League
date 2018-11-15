package com.nhlFantasy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.User;
import com.nhlFantasy.service.LeagueService;

@RestController
public class LeagueController {

	@Autowired
	LeagueService leagueService;
	
	@RequestMapping(value = "/api/addLeague", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody League addLeague(@RequestBody League league,HttpServletResponse response, HttpServletRequest  request) {

		League l = leagueService.addLeague(league);
		
		return l;
	}
}
