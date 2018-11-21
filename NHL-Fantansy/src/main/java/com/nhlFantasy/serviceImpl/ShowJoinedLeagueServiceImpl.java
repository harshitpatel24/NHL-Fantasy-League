package com.nhlFantasy.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueRepository;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.service.ShowJoinedLeagueService;

@Service
public class ShowJoinedLeagueServiceImpl implements ShowJoinedLeagueService{

	@Autowired
	LeagueRepository leagueRepository;
	
	@Override
	public ArrayList<League> getCreatedLeaguesService(int userId) {
		// TODO Auto-generated method stub
		ArrayList<League> leaguesJoined= new ArrayList<League>();
		leaguesJoined = leagueRepository.getJoinedLeague(userId);
		
		for(int i =0;i<leaguesJoined.size();i++) {
			League league = leaguesJoined.get(i);
			league.setLeaguePassword(null);
			league.getUser().setPassword(null);
		}
		return leaguesJoined;
	}

}
