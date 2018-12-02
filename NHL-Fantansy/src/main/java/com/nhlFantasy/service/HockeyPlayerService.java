package com.nhlFantasy.service;

import java.util.List;
import com.nhlFantasy.entity.HockeyPlayer;

public interface HockeyPlayerService {

	List<HockeyPlayer> findAllHockeyPlayerByTeam(String teamName);

	List<String> getTeams();

	List<HockeyPlayer> getAllPlayers();

	List<HockeyPlayer> getSelectedPlayersByMember(int userid, int leagueId);

	HockeyPlayer checkExistenceOfPlayer(int indexOf);

}
