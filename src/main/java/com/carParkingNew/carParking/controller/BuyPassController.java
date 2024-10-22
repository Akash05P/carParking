//package com.carParkingNew.carParking.controller;
//
//import com.carParkingNew.carParking.entity.BuyPass;
//import com.carParkingNew.carParking.entity.PackItem;
//import com.carParkingNew.carParking.entity.User;
//import com.carParkingNew.carParking.repository.UserRepo; // Assuming you have this repo
//import com.carParkingNew.carParking.service.BuyPassService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/buyPass")
//public class BuyPassController {
//
//    @Autowired
//    private BuyPassService buyPassService;
//
//    @Autowired
//    private UserRepo userRepo; // Assuming you have this repository for User
//
//    // Method to save a pass purchase
//    @PostMapping
//    public ResponseEntity<String> buyPass(@RequestParam Integer userId, @RequestParam Integer packItemId) {
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        PackItem packItem = new PackItem();
//        packItem.setCarId(packItemId); // Set the pack item ID
//
//        buyPassService.buyPass(user, packItem);
//
//        return ResponseEntity.ok("Pass purchase successful");
//    }
//
//    // Method to get all purchased passes by a user
//    @GetMapping
//    public ResponseEntity<List<PackItem>> getPurchasedPassesByUser(@RequestParam Integer userId) {
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<PackItem> purchasedPasses = buyPassService.getPurchasedPassesByUser(user);
//
//        return ResponseEntity.ok(purchasedPasses);
//    }
//
//    // Method to get the total number of passes purchased by all users
//    @GetMapping("/totalPassesPurchased")
//    public ResponseEntity<Integer> getTotalPassesPurchasedByAllUsers() {
//        int totalPassesPurchased = buyPassService.getTotalPassesPurchasedByAllUsers();
//        return ResponseEntity.ok(totalPassesPurchased);
//    }
//}
package com.carParkingNew.carParking.controller;

import com.carParkingNew.carParking.entity.BuyPass;
import com.carParkingNew.carParking.entity.PackItem;
import com.carParkingNew.carParking.entity.User;
import com.carParkingNew.carParking.repository.UserRepo; // Assuming you have this repo
import com.carParkingNew.carParking.service.BuyPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyPass")
public class BuyPassController {

    @Autowired
    private BuyPassService buyPassService;

    @Autowired
    private UserRepo userRepo; // Assuming you have this repository for User

    // Method to save a pass purchase
    @PostMapping
    public ResponseEntity<String> buyPass(@RequestParam Integer userId, @RequestParam Integer packItemId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PackItem packItem = new PackItem();
        packItem.setCarId(packItemId); // Ensure to properly initialize or retrieve the PackItem

        String response = buyPassService.buyPass(user, packItem);

        return ResponseEntity.ok(response); // Return the response from the service
    }

    // Method to get the purchased pass by a user
    @GetMapping
    public ResponseEntity<BuyPass> getPurchasedPassByUser(@RequestParam Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BuyPass purchasedPass = buyPassService.getPurchasedPassByUser(user)
                .orElseThrow(() -> new RuntimeException("No purchased pass found for this user"));

        return ResponseEntity.ok(purchasedPass);
    }

    // Method to get the total number of passes purchased by all users
    @GetMapping("/totalPassesPurchased")
    public ResponseEntity<Integer> getTotalPassesPurchasedByAllUsers() {
        int totalPassesPurchased = buyPassService.getTotalPassesPurchasedByAllUsers();
        return ResponseEntity.ok(totalPassesPurchased);
    }
}
