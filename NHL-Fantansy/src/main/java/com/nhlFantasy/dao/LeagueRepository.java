package com.nhlFantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.League;

public interface LeagueRepository extends JpaRepository<League, Long>{

	@Query(nativeQuery = true, value = "select count(*) from league l where l.leagueId = :leagueId and l.leaguePassowrd = :leaguePassword")
	int searchLeague(@Param("leagueId")int leagueId, @Param("leaguePassword") String leaguePassword);
	
	@Query(nativeQuery = true, value = "select leagueCapacity from league l where l.leagueId = :leagueId")
	int findLeagueCapacity(@Param("leagueId")int leagueId);
	
	@Query(nativeQuery = true, value = "select * from league l where l.leagueId = :leagueId")
	League findLeagueById(@Param("leagueId")int leagueId);
}
