//package com.carParkingNew.carParking.entity;
//
//import java.util.Collection;
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.carParkingNew.carParking.bean.AdminRoleBean;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter 
//@NoArgsConstructor
//@AllArgsConstructor
//public class Admin implements UserDetails{
//
//	private Integer id;
//	private String name;
//	private String password;
//	private String email;
//	private long phone;
//	private String address;
//
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "admin", cascade = CascadeType.ALL)
//	private Set<AdminRoleBean > roles;
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//	
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		
//		return null;
//	}
//}
//

package com.carParkingNew.carParking.entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements UserDetails {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String password;
	private String email;
	private long phone;
	private String address;
	 
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "admin", cascade = CascadeType.ALL)
	private Set<AdminRole> roles;
	
	
	

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return roles.stream()
	    		.map(role-> new SimpleGrantedAuthority("ROLE_"+role.getName()))
	            .collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	   @Override
	    public boolean isAccountNonExpired() {
	        return true; // Customize according to your logic
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true; // Customize according to your logic
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true; // Customize according to your logic
	    }

	    @Override
	    public boolean isEnabled() {
	        return true; // Customize according to your logic
	    }


	

}















