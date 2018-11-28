package com.nhlFantasy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.MemberTeamRepository;
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

}
