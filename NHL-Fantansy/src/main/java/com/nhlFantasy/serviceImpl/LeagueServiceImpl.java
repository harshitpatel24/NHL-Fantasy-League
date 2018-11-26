package com.nhlFantasy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueRepository;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.service.LeagueService;

@Service("LeagueService")
public class LeagueServiceImpl implements LeagueService{

	@Autowired
	LeagueRepository leagueRepository;
	@Override
	public League addLeague(League league)
	{
		League savedLeague  = null;
		try
		{
			savedLeague = leagueRepository.save(league);
		}
		catch(Exception e) 
		{
			savedLeague = new League();
			savedLeague.setLeagueId(-1);
		}
		
		return savedLeague;
	}
	@Override
	public League findLeague(int leagueId, String leaguePassword)
	{
		League league = null;
		
		try 
		{
		    league = leagueRepository.findLeague(leagueId, leaguePassword);
		}
		catch(Exception e) 
		{
			league = new League();
			league.setLeagueId(-1);
		}
		
		
		return league;
	}
	
	@Override
	public int findLeagueCapacity(int leagueId)
	{
		int leagueCapacity = leagueRepository.findLeagueCapacity(leagueId);
		return leagueCapacity;
	}
	
	@Override
	public League findLeagueById(int leagueId)
	{
		return leagueRepository.findLeagueById(leagueId);
	}
	@Override
	public League getLeagueByLeagueId(int leagueId) {
		// TODO Auto-generated method stub
		League league = null;
		
		try 
		{
		    league = leagueRepository.getLeagueByLeagueId(leagueId);
		}
		catch(Exception e) 
		{
			league = new League();
			league.setLeagueId(-1);
		}
		return league;
	}

}
