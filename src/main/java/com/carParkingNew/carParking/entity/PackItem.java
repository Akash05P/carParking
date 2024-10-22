package com.carParkingNew.carParking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackItem {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int carId;
	    private String pack;
	    private String price;
	    private String description;
	    private String duration;
	    private String discount;
		private LocalDateTime applieddate;
	  
	    @Lob
	    @Column(columnDefinition ="LONGTEXT")
	    private String image;

}
