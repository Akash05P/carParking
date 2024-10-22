package com.carParkingNew.carParking.service;


import java.util.List;
import com.carParkingNew.carParking.bean.AdminRequest;

public interface AdminService {
    AdminRequest createAdmin(AdminRequest request);
    AdminRequest updateAdmin(AdminRequest request, Integer adminId);
    AdminRequest getAdminById(Integer adminId);
    List<AdminRequest> getAllAdmins(Integer pageNumber, Integer pageSize);
    void deleteAdmin(Integer adminId);
}
