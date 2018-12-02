package com.nhlFantasy.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.HockeyPlayer;
import com.nhlFantasy.entity.LeagueMember;
import com.nhlFantasy.entity.MemberTeam;


public interface MemberTeamRepository extends JpaRepository<MemberTeam, Long>{
	@Modifying
	@Query(nativeQuery = true, value = "delete m from memberTeam m where m.memberId = :memberId")
	@Transactional
	void removeSelectedPlayers(@Param("memberId") int memberId);

	@Query(nativeQuery = true, value = "select * from memberTeam m where m.memberId = :memberId")
	List<MemberTeam> getSelectedPlayersByMembers(@Param("memberId") int memberId);
}
