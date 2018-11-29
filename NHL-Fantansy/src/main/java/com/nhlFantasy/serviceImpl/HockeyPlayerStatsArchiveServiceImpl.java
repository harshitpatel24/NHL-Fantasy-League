package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.HockeyPlayerStatsArchiveRepository;
import com.nhlFantasy.entity.HockeyPlayerStatsArchive;
import com.nhlFantasy.service.HockeyPlayerStatsArchiveService;

@Service("HockeyPlayerStatsArchiveService")
public class HockeyPlayerStatsArchiveServiceImpl implements HockeyPlayerStatsArchiveService{
	@Autowired
	HockeyPlayerStatsArchiveRepository hockeyPlayerStatsArchiveRepository;

	@Override
	public List<HockeyPlayerStatsArchive> getPointHistory(int leagueId, int userid) {
		// TODO Auto-generated method stub
		return hockeyPlayerStatsArchiveRepository.getPointHistory(leagueId,userid);
	}
}
