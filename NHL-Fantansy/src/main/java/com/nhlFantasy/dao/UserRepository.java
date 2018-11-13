package com.nhlFantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;

import com.nhlFantasy.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u")
	List<User> getAllUsers();

	@Query(nativeQuery = true, value = "select * from user u where u.password = :password and u.email = :email")
	User authenticateUser(@Param("password")String password, @Param("email")String email);
//	
//	@Query("select u from User u where u.emp_email = :emp_email AND u.emp_type = :emp_type")
//	User findByUserEmail(@Param("emp_email") String emp_email,@Param("emp_type") int emp_type);
	
//	@Query("select u from User u where u.uname like ? and u.email like ? and u.password like ?")
//	User getUser(@Param("uname")String uname, @Param("password")String password, @Param("email")String email);

}
