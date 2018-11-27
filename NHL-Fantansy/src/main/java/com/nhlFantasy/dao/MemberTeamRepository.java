package com.nhlFantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhlFantasy.entity.MemberTeam;

public interface MemberTeamRepository extends JpaRepository<MemberTeam, Long>{
}
