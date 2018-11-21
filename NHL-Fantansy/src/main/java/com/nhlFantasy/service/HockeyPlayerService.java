package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.HockeyPlayer;

public interface HockeyPlayerService {

	List<HockeyPlayer> findAllHockeyPlayerByTeam(String teamName);
}
