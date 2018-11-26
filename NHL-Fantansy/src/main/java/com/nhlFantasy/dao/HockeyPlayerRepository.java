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
	
	
}
