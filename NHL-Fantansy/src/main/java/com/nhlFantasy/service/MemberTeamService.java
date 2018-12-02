package com.nhlFantasy.service;

import java.util.List;

import com.nhlFantasy.entity.HockeyPlayer;
import com.nhlFantasy.entity.MemberTeam;

public interface MemberTeamService {
	MemberTeam addMemberTeam(MemberTeam memberTeam);

	void removeSelectedPlayers(int memberId);

	List<MemberTeam> getSelectedPlayersByMembers(int memberId);
}
