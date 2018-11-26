package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.HockeyPlayerRepository;
import com.nhlFantasy.entity.HockeyPlayer;
import com.nhlFantasy.service.HockeyPlayerService;

@Service("HockeyPlayerService")
public class HockeyPlayerServiceImpl implements HockeyPlayerService{

	@Autowired
	HockeyPlayerRepository hockeyPlayerRepository;
	@Override
	public List<HockeyPlayer> findAllHockeyPlayerByTeam(String teamName) {
		// TODO Auto-generated method stub
		//List<HockeyPlayer> listofHock = hockeyPlayerRepository.findAllHockeyPlayersByTeam(teamName);
		//System.out.println("1111" + listofHock.size());
		
		return hockeyPlayerRepository.findAllHockeyPlayersByTeam(teamName);
	}
	@Override
	public List<String> getTeams() {
		// TODO Auto-generated method stub
		return hockeyPlayerRepository.getTeams();
	}

}
