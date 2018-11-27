package com.nhlFantasy.service;

import java.util.ArrayList;
import com.nhlFantasy.entity.League;

public interface ShowCreatedLeagueService {
	ArrayList<League> getCreatedLeaguesService(int creatorId);
}
