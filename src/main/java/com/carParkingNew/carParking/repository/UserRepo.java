package com.carParkingNew.carParking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carParkingNew.carParking.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.name like:key")
	List<User> searchByName(@Param("key") String name);
	
	User findByEmail(String email);
}
