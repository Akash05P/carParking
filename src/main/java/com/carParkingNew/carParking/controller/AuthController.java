package com.carParkingNew.carParking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carParkingNew.carParking.entity.Admin;
import com.carParkingNew.carParking.entity.User;
import com.carParkingNew.carParking.security.JwtAuthRequest;
import com.carParkingNew.carParking.security.JwtAuthResponse;
import com.carParkingNew.carParking.security.JwtTokenHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {

        // Authenticate the user
        this.doAuthenticate(request.getEmail(), request.getPassword());

        // Load the user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        // Initialize variables for user-specific details
        Integer id = null;
        String name = null;
        List<String> roles = null;

        // Check if the user is an instance of Admin or User
        if (userDetails instanceof Admin) {
            Admin admin = (Admin) userDetails;
            id = admin.getId();
            name = admin.getName();
            roles = admin.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());

        } else if (userDetails instanceof User) {
            User user = (User) userDetails;
            id = user.getId();
            name = user.getName();
            roles = user.getUserRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());
        }

        // Generate the JWT token
        String token = jwtTokenHelper.generateToken(userDetails);

        // Build and return the JWT authentication response
        JwtAuthResponse response = JwtAuthResponse.builder()
                .jwttoken(token)
                .roles(roles)
                .email(userDetails.getUsername())  // Email (same as username)
                .name(name)
                .id(id)
                .username(userDetails.getUsername())  // Username (email)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Helper method to authenticate the user
    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }

    // Exception handler for bad credentials
    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentialsException() {
        return "Invalid Credentials!";
    }
}
