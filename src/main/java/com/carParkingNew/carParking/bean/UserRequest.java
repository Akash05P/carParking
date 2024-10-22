package com.carParkingNew.carParking.bean;

import java.util.Set;

import com.carParkingNew.carParking.entity.PackItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	private Integer id;
	private String name;
	private String email;
	private String useraddress;
	private long userphone;
	private String 	password;
	private Set<PackItem> packItems;
	
	
	private Set<UserRoleBean> studentRoles;

}
