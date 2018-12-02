package com.nhlFantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nhlFantasy.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u")
	List<User> getAllUsers();

	@Query(nativeQuery = true, value = "select * from user u where u.password = :password and u.email = :email")
	User authenticateUser(@Param("password")String password, @Param("email")String email);
	
	@Query(nativeQuery = true, value = "select * from user u where u.userid = :userid")
	User getUserById(@Param("userid") int userid);
	
	@Modifying
	@Query(nativeQuery = true, value = "update user u set u.uname = :uname, u.email = :email, u.password = :password where u.userid = :userid")
	@Transactional
	int updateUser(@Param("userid") int userid, @Param("uname") String uname, @Param("email") String email, @Param("password") String password);

	@Query(nativeQuery = true, value = "select * from user u where u.userid = :userid")
	User searchUserbyId(@Param("userid") int userid);
	
	@Query(nativeQuery = true, value = "select * from user u where u.email = :email")
	User searchUserbyEmail(@Param("email") String email);

}
