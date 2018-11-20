package com.nhlFantasy.service;

import com.nhlFantasy.entity.League;

public interface LeagueService {

	League addLeague(League league);
	int searchLeague(int leagueId, String leaguePassword);
	int findLeagueCapacity(int leagueId);
	League findLeagueById(int leagueId);
}
