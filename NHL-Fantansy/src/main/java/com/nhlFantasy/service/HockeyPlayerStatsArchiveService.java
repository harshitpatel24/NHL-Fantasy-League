package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.HockeyPlayerStatsArchive;

public interface HockeyPlayerStatsArchiveService {

	List<HockeyPlayerStatsArchive> getPointHistory(int leagueId, int userid);

}
