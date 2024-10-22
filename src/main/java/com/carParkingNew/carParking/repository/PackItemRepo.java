package com.carParkingNew.carParking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carParkingNew.carParking.entity.PackItem;

public interface PackItemRepo extends JpaRepository<PackItem, Integer> {

	 @Query("SELECT p FROM PackItem p WHERE p.pack = :pack OR p.price = :price")
	    List<PackItem> searchByPackOrPrice(@Param("pack") String pack, @Param("price") String price);
}
