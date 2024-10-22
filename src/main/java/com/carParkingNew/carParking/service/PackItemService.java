package com.carParkingNew.carParking.service;

import java.util.List;
import com.carParkingNew.carParking.bean.CarRequest;

public interface PackItemService {
    CarRequest createPackItem(CarRequest request);
    CarRequest updatePackItem(CarRequest request, Integer carId);
    CarRequest getPackItemById(Integer carId);
    List<CarRequest> getAllPackItems(Integer pageNumber, Integer pageSize);
    void deletePackItem(Integer carId);
    List<CarRequest> searchPackItems(String keyword, String price);
}
