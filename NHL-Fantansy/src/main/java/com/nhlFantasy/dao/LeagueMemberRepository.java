package com.nhlFantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.LeagueMember;

public interface LeagueMemberRepository extends JpaRepository<LeagueMember, Long>{

	@Query(nativeQuery = true, value = "select count(*) from leagueMember l where l.leagueid = :leagueid")
	int countLeagueMember(@Param("leagueid")int leagueid);
	
	
	
}
