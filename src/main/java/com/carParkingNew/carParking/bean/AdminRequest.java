package com.carParkingNew.carParking.bean;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminRequest {

	private Integer id;
	private String name;
	private String password;
	private String email;
	private long phone;
	private String address;
	
	private Set<AdminRoleBean> roles;
}
