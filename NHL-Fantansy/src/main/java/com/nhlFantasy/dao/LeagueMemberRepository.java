package com.nhlFantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.HockeyPlayerStatsArchive;
import com.nhlFantasy.entity.LeagueMember;

public interface LeagueMemberRepository extends JpaRepository<LeagueMember, Long>{

	@Query(nativeQuery = true, value = "select count(*) from leagueMember l where l.leagueid = :leagueid")
	int countLeagueMember(@Param("leagueid")int leagueid);
	
	@Query(nativeQuery = true, value = "select * from leagueMember l where l.leagueid = :leagueid and l.userid = :userid")
	LeagueMember findLeagueMemberbyUserId(@Param("leagueid") int leagueid, @Param("userid") int userid);
	
	@Query(nativeQuery = true, value = "select * from leagueMember l where l.leagueid = :leagueid order by points desc")
	List<LeagueMember> getLeagueLeadersByLeagueId(@Param("leagueid") int leagueid);
	
	
	
			
	@Query(nativeQuery = true, value = "select * from hockeyPlayerStatsArchive where playerId in (select playerId from memberTeam where memberId in (select id from leagueMember where userid = :userid and leagueId = :leagueid))")
	List<HockeyPlayerStatsArchive> getPointHistory(@Param("leagueid") int leagueId,@Param("userid") int userid);
}
