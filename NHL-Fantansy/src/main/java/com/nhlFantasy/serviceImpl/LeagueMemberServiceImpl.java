package com.nhlFantasy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueMemberRepository;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.service.LeagueMemberService;

@Service("LeagueMemberService")
public class LeagueMemberServiceImpl implements LeagueMemberService{

	@Autowired
	LeagueMemberRepository leagueMemberRepository;
	@Override
	public LeagueMember addLeagueMember(LeagueMember leagueMember) {
		return leagueMemberRepository.save(leagueMember);
	}
	
	@Override
	public int countLeagueMember(int leagueid) {
		int countLeagueMembers = leagueMemberRepository.countLeagueMember(leagueid);
		return countLeagueMembers;
	}
	
	@Override
	public LeagueMember findLeagueMemberbyUserid(int leagueid, int userid)
	{
		LeagueMember leagueMember = null;
		
		try
		{
			leagueMember = leagueMemberRepository.findLeagueMemberbyUserId(leagueid, userid);
		}
		catch(Exception e)
		{
			leagueMember = new LeagueMember();
			leagueMember.setId(-1);
		}
		
		return leagueMember ;
	}
}
