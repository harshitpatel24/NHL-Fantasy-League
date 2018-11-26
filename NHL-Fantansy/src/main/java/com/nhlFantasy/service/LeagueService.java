package com.nhlFantasy.service;

import com.nhlFantasy.entity.League;

public interface LeagueService {

	League addLeague(League league);
	League findLeague(int leagueId, String leaguePassword);
	int findLeagueCapacity(int leagueId);
	League findLeagueById(int leagueId);
	League getLeagueByLeagueId(int leagueId);
}
