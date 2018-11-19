package com.nhlFantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhlFantasy.entity.LeagueMember;

public interface LeagueMemberRepository extends JpaRepository<LeagueMember, Long>{

}
