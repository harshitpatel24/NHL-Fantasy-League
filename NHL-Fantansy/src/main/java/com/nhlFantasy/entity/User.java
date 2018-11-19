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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user",
uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	int userid;
	
	@Column(name="uname")
	String uname;
	
	@Column(name="email")
	String email;
	
	@Column(name="password")
	String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<League> league;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserLeague> userLeague;
	
	public Set<UserLeague> getUserLeague() {
		return userLeague;
	}
	public void setUserLeague(Set<UserLeague> userLeague) {
		this.userLeague = userLeague;
	}
	public Set<League> getLeague() {
		return league;
	}
	public void setLeague(Set<League> league) {
		this.league = league;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String username) {
		this.uname = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		
	
}
