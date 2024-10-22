package com.carParkingNew.carParking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carParkingNew.carParking.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.name like:key")
	List<Admin> searchByName(@Param("key") String name);
	
	Admin findByEmail(String email); 
}
