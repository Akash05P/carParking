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

import com.carParkingNew.carParking.bean.UserRequest;
import com.carParkingNew.carParking.entity.User;
import com.carParkingNew.carParking.entity.UserRole;
import com.carParkingNew.carParking.exception.ResourceNotFound;
import com.carParkingNew.carParking.repository.UserRepo;
import com.carParkingNew.carParking.repository.UserRoleRepo;
import com.carParkingNew.carParking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserRequest createUser(UserRequest request) {
        User user = requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepo.save(user);

        // Create default role
        UserRole defaultRole = new UserRole();
        defaultRole.setName("USER");
        defaultRole.setUser(savedUser);
        userRoleRepo.save(defaultRole);

        Set<UserRole> roles = new HashSet<>();
        roles.add(defaultRole);
        savedUser.setUserRoles(roles);

        return entityToRequest(savedUser);
    }

    @Override
    public UserRequest updateUser(UserRequest request, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User", "Id", userId));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUseraddress(request.getUseraddress());
        user.setUserphone(request.getUserphone());

        // Handle roles, etc.
        User updatedUser = userRepo.save(user);
        return entityToRequest(updatedUser);
    }

    @Override
    public UserRequest getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        return entityToRequest(user);
    }

    @Override
    public List<UserRequest> getAllUsers(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> pageUsers = userRepo.findAll(pageable);
        List<User> users = pageUsers.getContent();

        return users.stream().map(this::entityToRequest).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        userRepo.delete(user);
    }

    private User requestToEntity(UserRequest request) {
        return modelMapper.map(request, User.class);
    }

    private UserRequest entityToRequest(User user) {
        return modelMapper.map(user, UserRequest.class);
    }
}