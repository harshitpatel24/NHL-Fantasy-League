package com.nhlFantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u")
	List<User> getAllUsers();

}
