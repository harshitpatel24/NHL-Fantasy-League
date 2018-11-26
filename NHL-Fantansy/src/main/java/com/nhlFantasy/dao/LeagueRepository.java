package com.nhlFantasy.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.League;
import com.nhlFantasy.entity.User;

public interface LeagueRepository extends JpaRepository<League, Long>{

//	@Query(nativeQuery = true, value = "select count(*) from league l where l.leagueId = :leagueId AND l.leaguePassword = :leaguePassword")
//	int findLeague(@Param("leagueId")int leagueId, @Param("leaguePassword") String leaguePassword);
	
//	@Query(nativeQuery = true, value = "select * from league l where l.leagueId = :leagueId AND l.leaguePassword = :leaguePassword")
//	League findLeague(@Param("leagueId")int leagueId, @Param("leaguePassword") String leaguePassword);
//	
	@Query(nativeQuery = true, value = "select leagueCapacity from league l where l.leagueId = :leagueId")
	int findLeagueCapacity(@Param("leagueId")int leagueId);
	
	@Query(nativeQuery = true, value = "select * from league l where l.leagueId = :leagueId")
	League findLeagueById(@Param("leagueId")int leagueId);
	
	@Query(nativeQuery = true, value = "select * from league l where l.leagueId = :leagueId AND l.leaguePassword = :leaguePassword")
	League findLeague(@Param("leagueId")int leagueId,@Param("leaguePassword") String leaguePassword);
	
	@Query(nativeQuery = true, value = "select * from League l where l.creatorId = :creatorId")
	ArrayList<League> getCreatedLeague(@Param("creatorId")int creatorId);
	
	@Query(nativeQuery = true, value = "select * from League l where l.leagueId = :leagueId")
	League getLeagueByLeagueId(@Param("leagueId")int leagueId);
	
	@Query(nativeQuery = true, value =  "select l.* from league l , leaguemember lm " + 
										"where l.creatorId <> :userId " + 
										"and l.leagueid = lm.leagueid " + 
										"and lm.userid = :userId " + 
										"order by l.leagueId asc")
	ArrayList<League> getJoinedLeague(@Param("userId")int userId);
}
