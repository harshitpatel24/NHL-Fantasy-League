package com.nhlFantasy.service;

import java.sql.Date;
import java.util.List;

import com.nhlFantasy.entity.MatchSchedule;

public interface MatchScheduleService {
	List<MatchSchedule> getMatchScheduleByDate(Date date);
}
