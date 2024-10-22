package com.carParkingNew.carParking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carParkingNew.carParking.bean.ApiResponse;
import com.carParkingNew.carParking.bean.CarRequest;
import com.carParkingNew.carParking.service.PackItemService;

@RestController
@CrossOrigin("https://localhost:3000/")
@RequestMapping("/api/packItem")
public class PackItemController {

    @Autowired
    private PackItemService packItemService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/create")
    public ResponseEntity<CarRequest> createPackItem(@RequestBody CarRequest request) {
        CarRequest createdRequest = this.packItemService.createPackItem(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<CarRequest> updatePackItem(@RequestBody CarRequest request, @PathVariable Integer carId) {
        CarRequest updatedPackItem = this.packItemService.updatePackItem(request, carId);
        return ResponseEntity.ok(updatedPackItem);
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<ApiResponse> deletePackItem(@PathVariable Integer carId) {
        this.packItemService.deletePackItem(carId);
        return new ResponseEntity<>(new ApiResponse("PackItem deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CarRequest>> getAllPackItems(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return ResponseEntity.ok(this.packItemService.getAllPackItems(pageNumber, pageSize));
    }

    @GetMapping("/get/{carId}")
    public ResponseEntity<CarRequest> getPackItem(@PathVariable Integer carId) {
        return ResponseEntity.ok(this.packItemService.getPackItemById(carId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarRequest>> searchPackItems(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "price", required = false) String price) {
        List<CarRequest> result = this.packItemService.searchPackItems(keyword, price);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
