package com.nhlFantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhlFantasy.entity.League;

public interface LeagueRepository extends JpaRepository<League, Long>{

	
}
