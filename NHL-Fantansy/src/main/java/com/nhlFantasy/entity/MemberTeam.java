package com.nhlFantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "memberTeam")
public class MemberTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int teamId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberId")
	LeagueMember leagueMember; 
	
	public HockeyPlayer getHockeyPlayer() {
		return hockeyPlayer;
	}

	public void setHockeyPlayer(HockeyPlayer hockeyPlayer) {
		this.hockeyPlayer = hockeyPlayer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playerId")
	HockeyPlayer hockeyPlayer;
	
	@Column(name = "pointsEarned")
	int pointsEarned;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public LeagueMember getLeagueMember() {
		return leagueMember;
	}

	public void setLeagueMember(LeagueMember leagueMember) {
		this.leagueMember = leagueMember;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}
	
	//@Column(name = "activeStatus")
	//int activeStatus;
	
	
}
