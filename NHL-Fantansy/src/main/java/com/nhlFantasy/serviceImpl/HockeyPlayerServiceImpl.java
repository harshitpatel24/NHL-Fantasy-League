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
	@Override
	public List<HockeyPlayer> getAllPlayers() {
		// TODO Auto-generated method stub
		return hockeyPlayerRepository.getAllPlayers();
	}
	@Override
	public List<HockeyPlayer> getSelectedPlayersByMember(int userid, int leagueId) {
		// TODO Auto-generated method stub
		return hockeyPlayerRepository.getSelectedPlayersByMember(userid,leagueId); 
	}
	@Override
	public HockeyPlayer checkExistenceOfPlayer(int playerId) {
		// TODO Auto-generated method stub
		return hockeyPlayerRepository.checkExistenceOfPlayer(playerId);
	}

}
