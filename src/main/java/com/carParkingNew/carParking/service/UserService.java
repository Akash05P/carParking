package com.carParkingNew.carParking.service;

import java.util.List;

import com.carParkingNew.carParking.bean.UserRequest;

public interface UserService {

    UserRequest createUser(UserRequest request);

    UserRequest updateUser(UserRequest request, Integer userId);

    UserRequest getUserById(Integer userId);

    List<UserRequest> getAllUsers(Integer pageNumber, Integer pageSize);

    void deleteUser(Integer userId);
}