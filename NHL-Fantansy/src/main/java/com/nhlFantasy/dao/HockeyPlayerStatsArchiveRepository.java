package com.nhlFantasy.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhlFantasy.entity.HockeyPlayerStatsArchive;;

public interface HockeyPlayerStatsArchiveRepository extends JpaRepository<HockeyPlayerStatsArchive, Long>{
	
	@Query(nativeQuery = true, value = "select * from hockeyPlayerStatsArchive where playerId in(select playerId from hockeyPlayer where playerId in (select playerId from memberTeamArchive where memberId in (select id from leagueMember where userid = :userid and leagueId = :leagueid)))")
	List<HockeyPlayerStatsArchive> getPointHistory(@Param("leagueid") int leagueId,@Param("userid") int userid);

}
