package com.carParkingNew.carParking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carParkingNew.carParking.entity.BuyPass;
import com.carParkingNew.carParking.entity.PackItem;
import com.carParkingNew.carParking.entity.User;

public interface BuyPassRepo extends JpaRepository<BuyPass, Integer>{

//	Optional<BuyPass> findByUserAndPackItem(User user, PackItem packItem);
	 Optional<BuyPass> findByUser(User user);
	    
}
