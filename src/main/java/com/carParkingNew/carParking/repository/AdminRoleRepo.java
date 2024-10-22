package com.carParkingNew.carParking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carParkingNew.carParking.entity.AdminRole;

public interface AdminRoleRepo extends JpaRepository<AdminRole, Integer>{
	
}
