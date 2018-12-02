package com.nhlFantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.HockeyPlayer;

public interface HockeyPlayerRepository extends JpaRepository<HockeyPlayer, Long>{

	@Query(nativeQuery = true, value = "select * from hockeyPlayer h where h.teamName = :teamName")
	List<HockeyPlayer> findAllHockeyPlayersByTeam(@Param("teamName") String teamName);
	
	@Query(nativeQuery = true, value = "select distinct(h.teamName) from hockeyPlayer h")
	List<String> getTeams();

	@Query(nativeQuery = true, value = "select * from hockeyPlayer h")
	List<HockeyPlayer> getAllPlayers();

	@Query(nativeQuery = true, value = "select * from hockeyPlayer where playerId in (select playerId from memberTeam where memberId in (select id from leagueMember where  userid = :userid and leagueid = :leagueId))")
	List<HockeyPlayer> getSelectedPlayersByMember(@Param("userid") int userid,@Param("leagueId") int leagueId);

	
	@Query(nativeQuery = true, value = "select * from hockeyPlayer h where h.playerId = :playerId")
	HockeyPlayer checkExistenceOfPlayer(@Param("playerId") int playerId);

	
		
}
