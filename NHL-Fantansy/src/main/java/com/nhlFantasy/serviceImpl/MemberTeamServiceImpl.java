package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.MemberTeamRepository;
import com.nhlFantasy.entity.HockeyPlayer;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.MemberTeam;
import com.nhlFantasy.service.MemberTeamService;

@Service("MemberTeamService")
public class MemberTeamServiceImpl implements MemberTeamService {

	@Autowired
	MemberTeamRepository memberTeamRepository;
	@Override
	public MemberTeam addMemberTeam(MemberTeam memberTeam) {
		// TODO Auto-generated method stub
		return memberTeamRepository.save(memberTeam);
	}
	@Override
	public void removeSelectedPlayers(int memberId) {
		// TODO Auto-generated method stub
		memberTeamRepository.removeSelectedPlayers(memberId);
	}
	
	@Override
	public List<MemberTeam> getSelectedPlayersByMembers(int memberId) {
		// TODO Auto-generated method stub
		return memberTeamRepository.getSelectedPlayersByMembers(memberId);
	}

}
