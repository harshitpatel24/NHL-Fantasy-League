package com.nhlFantasy.entity;

import java.sql.Date;
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
@Table(name = "memberTeamArchive")
public class MemberTeamArchive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int memberTeamArchiveId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberId")
	LeagueMember leagueMember; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playerId")
	HockeyPlayer hockeyPlayer;
	
	public int getMemberTeamArchiveId() {
		return memberTeamArchiveId;
	}

	public void setMemberTeamArchiveId(int memberTeamArchiveId) {
		this.memberTeamArchiveId = memberTeamArchiveId;
	}

	public LeagueMember getLeagueMember() {
		return leagueMember;
	}

	public void setLeagueMember(LeagueMember leagueMember) {
		this.leagueMember = leagueMember;
	}

	public HockeyPlayer getHockeyPlayer() {
		return hockeyPlayer;
	}

	public void setHockeyPlayer(HockeyPlayer hockeyPlayer) {
		this.hockeyPlayer = hockeyPlayer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "date")
	Date date;

}
