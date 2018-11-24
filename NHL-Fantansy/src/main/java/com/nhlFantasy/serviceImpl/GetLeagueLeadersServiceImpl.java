package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueMemberRepository;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.service.GetLeagueLeadersService;

@Service("GetLeagueLeadersService")
public class GetLeagueLeadersServiceImpl implements GetLeagueLeadersService{
	@Autowired
	LeagueMemberRepository leagueMemberRepository;

	@Override
	public List<LeagueMember> getLeagueLeaders(int leagueId){
		// TODO Auto-generated method stub
		return leagueMemberRepository.getLeagueLeadersByLeagueId(leagueId);
	}
}
