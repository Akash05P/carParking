package com.carParkingNew.carParking.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.carParkingNew.carParking.bean.CarRequest;
import com.carParkingNew.carParking.entity.PackItem;
import com.carParkingNew.carParking.exception.ResourceNotFound;
import com.carParkingNew.carParking.repository.PackItemRepo;
import com.carParkingNew.carParking.service.PackItemService;

import jakarta.transaction.Transactional;

@Service
public class PackItemServiceImpl implements PackItemService {

    @Autowired
    private PackItemRepo packItemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CarRequest createPackItem(CarRequest request) {
        PackItem packItem = requestToEntity(request);
        PackItem savedPackItem = this.packItemRepo.save(packItem);
        return entityToRequest(savedPackItem);
    }

    @Override
    public CarRequest updatePackItem(CarRequest request, Integer carId) {
        PackItem packItem = this.packItemRepo.findById(carId).orElseThrow(() -> 
            new ResourceNotFound("PackItem", "Id", carId));

        packItem.setPack(request.getPack());
        packItem.setPrice(request.getPrice());
        packItem.setDescription(request.getDescription());
        packItem.setImage(request.getImage());
        packItem.setDuration(request.getDuration());
        packItem.setDiscount(request.getDiscount());
        packItem.setApplieddate(request.getApplieddate());
        
        PackItem updatedPackItem = this.packItemRepo.save(packItem);
        return entityToRequest(updatedPackItem);
    }

    @Override
    public CarRequest getPackItemById(Integer carId) {
        PackItem packItem = this.packItemRepo.findById(carId).orElseThrow(() -> 
            new ResourceNotFound("PackItem", "Id", carId));
        return entityToRequest(packItem);
    }

    @Override
    public List<CarRequest> getAllPackItems(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PackItem> page = this.packItemRepo.findAll(pageable);
        List<PackItem> packItems = page.getContent();

        return packItems.stream()
            .map(this::entityToRequest)
            .collect(Collectors.toList());
    }

    @Override
    @Modifying
    @Transactional
    public void deletePackItem(Integer carId) {
        PackItem packItem = this.packItemRepo.findById(carId).orElseThrow(() -> 
            new ResourceNotFound("PackItem", "Id", carId));
        this.packItemRepo.delete(packItem);
    }

    @Override
    public List<CarRequest> searchPackItems(String keyword, String price) {
        List<PackItem> packItems = this.packItemRepo.searchByPackOrPrice("%" + keyword + "%", price);
        return packItems.stream()
            .map(this::entityToRequest)
            .collect(Collectors.toList());
    }

    private PackItem requestToEntity(CarRequest request) {
        return this.modelMapper.map(request, PackItem.class);
    }

    private CarRequest entityToRequest(PackItem packItem) {
        return this.modelMapper.map(packItem, CarRequest.class);
    }
}
