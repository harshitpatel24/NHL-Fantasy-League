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
	public League addLeague(League league) {
		League savedLeague  = null;
		try {
			savedLeague = leagueRepository.save(league);
		}catch(Exception e) {
			//e.printStackTrace();
			savedLeague = new League();
			savedLeague.setLeagueId(-1);
		}
		
//		if (savedLeague == null)
//		{
//			
//			
//			return savedLeague;
//		}
		
		return savedLeague;
	}
	@Override
	public int searchLeague(int leagueId, String leaguePassword) {
		// TODO Auto-generated method stub
		
		int count = leagueRepository.searchLeague(leagueId,leaguePassword);
		
		return count;
	}
	@Override
	public int findLeagueCapacity(int leagueId) {
		// TODO Auto-generated method stub
		int leagueCapacity = leagueRepository.findLeagueCapacity(leagueId);
		return leagueCapacity;
	}
	@Override
	public League findLeagueById(int leagueId) {
		// TODO Auto-generated method stub
		leagueRepository.findLeagueById(leagueId);
		return leagueRepository.findLeagueById(leagueId);
	}

}
