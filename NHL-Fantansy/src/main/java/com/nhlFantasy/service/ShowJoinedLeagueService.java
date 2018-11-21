package com.nhlFantasy.service;

import java.util.ArrayList;

import com.nhlFantasy.entity.League;

public interface ShowJoinedLeagueService {
	
	ArrayList<League> getCreatedLeaguesService(int userId);
}
