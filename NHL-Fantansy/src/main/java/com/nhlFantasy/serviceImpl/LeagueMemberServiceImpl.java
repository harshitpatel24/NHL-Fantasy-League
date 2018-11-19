package com.nhlFantasy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueMemberRepository;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.service.LeagueMemberService;

@Service
public class LeagueMemberServiceImpl implements LeagueMemberService{

	@Autowired
	LeagueMemberRepository leagueMemberRepository;
	@Override
	public LeagueMember addLeagueMember(LeagueMember leagueMember) {
		// TODO Auto-generated method stub
		return leagueMemberRepository.save(leagueMember);
	}

}
