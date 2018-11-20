package com.nhlFantasy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.LeagueMemberRepository;
import com.nhlFantasy.entity.League;
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
	@Override
	public int countLeagueMember(int leagueid) {
		// TODO Auto-generated method stub
		int countLeagueMembers = leagueMemberRepository.countLeagueMember(leagueid);
		return countLeagueMembers;
	}
	@Override
	public LeagueMember findLeagueMemberbyUserid(int leagueid, int userid) {
		// TODO Auto-generated method stub
		LeagueMember leagueMember = null;
		League league = null;
		try {
			leagueMember = leagueMemberRepository.findLeagueMemberbyUserId(leagueid, userid);
			//System.out.println(leagueMember.getLeague().getLeagueId());
			//System.out.println("\n\nyes it printds\n\n");
		}catch(Exception e) {
			//System.out.println("here in except");
			leagueMember = new LeagueMember();
			league = new League();
			league.setLeagueId(-1);
			leagueMember.setLeague(league);
		}
		
		return leagueMember ;
	}

}
