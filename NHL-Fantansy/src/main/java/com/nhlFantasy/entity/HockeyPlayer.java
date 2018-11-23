package com.nhlFantasy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hockeyPlayer")
public class HockeyPlayer implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playerId")
	int playerId;
	
	@Column(name = "playerRank")
	int rank;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "teamName")
	String teamName;
	
	@Column(name = "teamAbbr")
	String teamAbbr;
	
	@Column(name = "position")
	String position;

	@Column(name = "tShirtNo")
	int tShirtNo;
	
	@Column(name = "age")
	int age;
	
	@Column(name = "height")
	String height;
	
	@Column(name = "weight")
	int weight;
	
	@Column(name = "birthday")
	String birthday;
	
	@Column(name = "playerValue")
	int playerValue;
	
	public String getTeamAbbr() {
		return teamAbbr;
	}

	public void setTeamAbbr(String teamAbbr) {
		this.teamAbbr = teamAbbr;
	}

	public int getPlayerValue() {
		return playerValue;
	}

	public void setPlayerValue(int playerValue) {
		this.playerValue = playerValue;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPosition() {
		return position;
	}

	public int gettShirtNo() {
		return tShirtNo;
	}

	public void settShirtNo(int tShirtNo) {
		this.tShirtNo = tShirtNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setPosition(String position) {
		this.position = position;
	}	
	
	
}
