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
@Table(name = "hockeyPlayerStatsArchive")
public class HockeyPlayerStatsArchive {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int hockeyPlayerStatsArchiveId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playerId")
	HockeyPlayer hockeyPlayer;
	
	public int getHockeyPlayerStatsArchiveId() {
		return hockeyPlayerStatsArchiveId;
	}

	public void setHockeyPlayerStatsArchiveId(int hockeyPlayerStatsArchiveId) {
		this.hockeyPlayerStatsArchiveId = hockeyPlayerStatsArchiveId;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPointSummary() {
		return pointSummary;
	}

	public void setPointSummary(String pointSummary) {
		this.pointSummary = pointSummary;
	}

	@Column(name = "date")
	Date date;
	
	@Column(name = "points")
	int points;
	
	@Column(name="pointSummary")
	String pointSummary;
	
}
