package com.carParkingNew.carParking.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carParkingNew.carParking.bean.AdminRequest;
import com.carParkingNew.carParking.service.AdminService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<AdminRequest> createAdmin(@RequestBody AdminRequest request) {
        AdminRequest createdAdmin = adminService.createAdmin(request);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminRequest> updateAdmin(@RequestBody AdminRequest request, @PathVariable Integer id) {
        AdminRequest updatedAdmin = adminService.updateAdmin(request, id);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AdminRequest>> getAllAdmins(
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<AdminRequest> admins = adminService.getAllAdmins(pageNumber, pageSize);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdminRequest> getAdmin(@PathVariable Integer id) {
        AdminRequest admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }
}