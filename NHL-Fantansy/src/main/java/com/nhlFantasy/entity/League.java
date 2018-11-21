package com.nhlFantasy.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "league")
public class League implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leagueId")
	int leagueId;
	
	@Column(name = "leagueName")
	String leagueName;
	
	@Column(name = "leagueCapacity")
	int leagueCapacity;
	
	@Column(name = "leaguePassword")
	String leaguePassword;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creatorId")
	User user; 
	
	public int getLeagueId() {
		return leagueId;
	}
	
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	
	public String getLeagueName() {
		return leagueName;
	}
	
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	
	public int getLeagueCapacity() {
		return leagueCapacity;
	}
	
	public void setLeagueCapacity(int leagueCapacity) {
		this.leagueCapacity = leagueCapacity;
	}
	
	public String getLeaguePassword() {
		return leaguePassword;
	}
	
	public void setLeaguePassword(String leaguePassword) {
		this.leaguePassword = leaguePassword;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
