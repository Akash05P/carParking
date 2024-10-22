package com.carParkingNew.carParking.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carParkingNew.carParking.entity.Admin;
import com.carParkingNew.carParking.entity.User;
import com.carParkingNew.carParking.repository.AdminRepo;
import com.carParkingNew.carParking.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private UserRepo userRepo;

    public CustomUserDetailsService(AdminRepo adminRepo, UserRepo userRepo) {
        this.adminRepo = adminRepo;
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First, try to find the user in the User table
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return user;
        }
        
        // If the user is not found in the User table, try the Admin table
        Admin admin = adminRepo.findByEmail(email);
        if (admin != null) {
            return admin;
        }
        
        // If the user is not found in both, throw an exception
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
