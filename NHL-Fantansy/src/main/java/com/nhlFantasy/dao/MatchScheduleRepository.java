package com.nhlFantasy.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nhlFantasy.entity.MatchSchedule;



public interface MatchScheduleRepository extends JpaRepository<MatchSchedule, Long>{
	@Query(nativeQuery = true, value = "select * from matchSchedule m where m.date = :date")
	List<MatchSchedule> getMatchByDate(@Param("date")Date date);
}
