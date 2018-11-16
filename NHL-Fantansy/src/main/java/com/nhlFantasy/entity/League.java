package com.nhlFantasy.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "league")
public class League {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leagueId")
	int leagueId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creatorId")
	User user; 
	
//	@NaturalId
//	@Column(name = "creatorId")
//	int creatorId;
	
	@Column(name = "leagueName")
	String leagueName;
	
	@Column(name = "leagueCapacity")
	int leagueCapacity;
	
	@Column(name = "leaguePassword")
	String leaguePassword;
	
	public String getLeaguePassword() {
		return leaguePassword;
	}
	
	public void setLeaguePassword(String leaguePassword) {
		this.leaguePassword = leaguePassword;
	}
	
	public int getLeagueId() {
		return leagueId;
	}
	
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	
	public User getCreator() {
		return user;
	}
	
	public void setCreator(User creator) {
		this.user = creator;
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
	
	
}
