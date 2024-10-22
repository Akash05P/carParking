package com.carParkingNew.carParking.serviceImpl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carParkingNew.carParking.bean.AdminRequest;
import com.carParkingNew.carParking.entity.Admin;
import com.carParkingNew.carParking.entity.AdminRole;
import com.carParkingNew.carParking.exception.ResourceNotFound;
import com.carParkingNew.carParking.repository.AdminRepo; // Assuming you have an AdminRepo
import com.carParkingNew.carParking.repository.AdminRoleRepo;
import com.carParkingNew.carParking.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private AdminRoleRepo adminRoleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public AdminRequest createAdmin(AdminRequest request) {
//        Admin admin = this.requestToEntity(request);
//        admin.setPassword(passwordEncoder.encode(request.getPassword())); // Encode password
//        Admin savedAdmin = this.adminRepo.save(admin);
//        return this.entityToRequest(savedAdmin);
//    }
    
    @Override
    public AdminRequest createAdmin(AdminRequest request) {
        // Convert request to Admin entity
        Admin admin = this.requestToEntity(request);
        
        // Encode the password
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Save the Admin entity
        Admin savedAdmin = this.adminRepo.save(admin);
        
        // Create default "ADMIN" role
        AdminRole defaultRole = new AdminRole();
        defaultRole.setName("ADMIN");
        defaultRole.setAdmin(savedAdmin);
        
        // Save the role in the database
        adminRoleRepo.save(defaultRole);
        
        // Assign the role to the admin
        Set<AdminRole> roles = new HashSet<>();
        roles.add(defaultRole);
        savedAdmin.setRoles(roles);
        
        // Return the created admin as a response
        return this.entityToRequest(savedAdmin);
    }


    @Override
    public AdminRequest updateAdmin(AdminRequest request, Integer adminId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFound("Admin", "Id", adminId));
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword())); // Encode new password
        admin.setPhone(request.getPhone());
        admin.setAddress(request.getAddress());
        Admin updatedAdmin = adminRepo.save(admin);
        return this.entityToRequest(updatedAdmin);
    }

    @Override
    public AdminRequest getAdminById(Integer adminId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFound("Admin", "Id", adminId));
        return this.entityToRequest(admin);
    }

    @Override
    public List<AdminRequest> getAllAdmins(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Admin> page = adminRepo.findAll(pageable);
        return page.getContent().stream()
                .map(this::entityToRequest)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFound("Admin", "Id", adminId));
        adminRepo.delete(admin);
    }

    private Admin requestToEntity(AdminRequest request) {
        return modelMapper.map(request, Admin.class);
    }

    private AdminRequest entityToRequest(Admin admin) {
        return modelMapper.map(admin, AdminRequest.class);
    }
}