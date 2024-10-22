package com.carParkingNew.carParking.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@NoArgsConstructor
@Builder
@ToString
public class JwtAuthResponse {

	private String jwttoken;
	private List<String> roles;
	private String email;
	private String name;
	private Integer id;
	private String username;
	
	public JwtAuthResponse(String jwttoken, List<String> roles, String email, String name, Integer id,
			String username) {
		super();
		this.jwttoken = jwttoken;
		this.roles = roles;
		this.email = email;
		this.name = name;
		this.id = id;
		this.username = username;
	}
	
	
}
