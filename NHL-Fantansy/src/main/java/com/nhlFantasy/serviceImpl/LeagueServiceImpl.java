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
		// TODO Auto-generated method stub
		//leagueRepository.save(league);
		
		//System.out.println(league.getUser().getUserid());
		return leagueRepository.save(league);
	}

}
