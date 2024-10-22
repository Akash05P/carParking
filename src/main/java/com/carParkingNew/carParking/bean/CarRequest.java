package com.carParkingNew.carParking.bean;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

	 private int carId;
		private String pack;
		private String price;
		private String image;
		private String description;
		private String duration;
		private String discount;
		private LocalDateTime applieddate;
	
		
}
