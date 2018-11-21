package com.nhlFantasy.service;

import com.nhlFantasy.entity.LeagueMember;

public interface LeagueMemberService {

	LeagueMember addLeagueMember(LeagueMember leagueMember);
	int countLeagueMember(int leagueid);
	LeagueMember findLeagueMemberbyUserid(int leagueid, int userid);
}
