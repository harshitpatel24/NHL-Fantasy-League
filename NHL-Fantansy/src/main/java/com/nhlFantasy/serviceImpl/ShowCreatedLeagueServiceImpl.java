package com.nhlFantasy.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueRepository;
import com.nhlFantasy.entity.League;
import com.nhlFantasy.service.ShowCreatedLeagueService;
@Service
public class ShowCreatedLeagueServiceImpl implements ShowCreatedLeagueService {
	@Autowired
	LeagueRepository leagueRepository;
	@Override
	public ArrayList<League> getCreatedLeaguesService(int creatorId) {
		ArrayList<League> leaguesCreated= new ArrayList<League>();
		leaguesCreated = leagueRepository.getCreatedLeague(creatorId);
		return leaguesCreated;
	}

}
