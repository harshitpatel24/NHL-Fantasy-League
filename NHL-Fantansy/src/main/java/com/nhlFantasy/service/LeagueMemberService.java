package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.HockeyPlayerStatsArchive;
import com.nhlFantasy.entity.LeagueMember;

public interface LeagueMemberService {

	LeagueMember addLeagueMember(LeagueMember leagueMember);
	int countLeagueMember(int leagueid);
	LeagueMember findLeagueMemberbyUserid(int leagueid, int userid);
}
