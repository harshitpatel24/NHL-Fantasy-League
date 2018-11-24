package com.nhlFantasy.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhlFantasy.dao.MatchScheduleRepository;
import com.nhlFantasy.entity.MatchSchedule;
import com.nhlFantasy.service.MatchScheduleService;

@Service("MatchScheduleService")
public class MatchScheduleServiceImpl implements MatchScheduleService{
	@Autowired
	MatchScheduleRepository matchScheduleRepository;
	@Override
	public List<MatchSchedule> getMatchScheduleByDate(Date date) {
		// TODO Auto-generated method stub
		return matchScheduleRepository.getMatchByDate(date);
	}

}
