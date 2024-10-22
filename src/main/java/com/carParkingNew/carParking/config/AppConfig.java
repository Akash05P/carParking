package com.carParkingNew.carParking.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.modelmapper.ModelMapper;

//import com.example.appointment.filter.SimpleCORSFilter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
//@EnableWebMvc
public class AppConfig {
	
	@Autowired
    private Environment env; // Inject the Environment object

	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user= User.builder()
//				.username("akash")
//				.password(passwordEncoder()
//				.encode("akash05"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user);
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	 
}

	
