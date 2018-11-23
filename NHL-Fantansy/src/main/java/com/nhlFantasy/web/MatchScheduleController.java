package com.nhlFantasy.web;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import com.nhlFantasy.entity.MatchSchedule;
import com.nhlFantasy.service.MatchScheduleService;

@RestController
public class MatchScheduleController {
	
	@Autowired
	MatchScheduleService matchScheduleService;

	
	Date parseDate(String dateStr) 
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
		try {
			parsed = (java.util.Date) format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Date sqlDate = new Date(parsed.getTime());
		return sqlDate;
	}
	
	@RequestMapping(value = "/api/getMatchByDate", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonNode getMatchByDate(@RequestBody JsonNode objNode,  HttpServletResponse response, HttpServletRequest  request,HttpSession session) {
	
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<MatchSchedule> matchSchedule = new ArrayList<MatchSchedule>();
		JsonNode node = null;
		
		if(session.getAttribute("userid") == null)
		{
			node = mapper.convertValue(matchSchedule, JsonNode.class);
		}
		else
		{
			String dateStr = objNode.get("date").toString();
			dateStr = dateStr.substring(1, dateStr.length()- 1);
			Date dateObj =  parseDate(dateStr);
			matchSchedule = (ArrayList<MatchSchedule>) matchScheduleService.getMatchScheduleByDate(dateObj);
			
			//System.out.println("list of hockey players = " + hockeyPlayers.size());
			
			node = mapper.convertValue(matchSchedule, JsonNode.class);
		}
		return node;
	}	 
}
