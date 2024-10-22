package com.carParkingNew.carParking.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carParkingNew.carParking.bean.ApiResponse;
import com.carParkingNew.carParking.bean.UserRequest;
import com.carParkingNew.carParking.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest request) {
        UserRequest createdUser = userService.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest request, @PathVariable Integer id) {
        UserRequest updatedUser = userService.updateUser(request, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserRequest>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return ResponseEntity.ok(userService.getAllUsers(pageNumber, pageSize));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserRequest> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}