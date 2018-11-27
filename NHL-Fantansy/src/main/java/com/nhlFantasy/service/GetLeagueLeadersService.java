package com.nhlFantasy.service;
import java.util.List;
import com.nhlFantasy.entity.LeagueMember;

public interface GetLeagueLeadersService {
	List<LeagueMember> getLeagueLeaders(int leagueId);
}