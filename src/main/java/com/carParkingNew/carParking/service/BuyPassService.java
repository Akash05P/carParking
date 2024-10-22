//package com.carParkingNew.carParking.service;
//
//import com.carParkingNew.carParking.entity.BuyPass;
//import com.carParkingNew.carParking.entity.PackItem;
//import com.carParkingNew.carParking.entity.User;
//import com.carParkingNew.carParking.repository.BuyPassRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class BuyPassService {
//
//    @Autowired
//    private BuyPassRepo buyPassRepo;
//
//    // Method to record a pass purchase
//    public void buyPass(User user, PackItem packItem) {
//        Optional<BuyPass> existingPurchase = buyPassRepo.findByUserAndPackItem(user, packItem);
//
//        if (existingPurchase.isPresent()) {
//            throw new RuntimeException("Pack already purchased by this user");
//        } else {
//            BuyPass newPurchase = new BuyPass(null, user, packItem);
//            buyPassRepo.save(newPurchase); // Save the new purchase record
//        }
//    }
//
//    // Method to get all purchased passes for a specific user
//    public List<PackItem> getPurchasedPassesByUser(User user) {
//        List<BuyPass> purchasedPasses = buyPassRepo.findByUser(user);
//        return purchasedPasses.stream()
//                .map(BuyPass::getPackItem) // Map to get the PackItem from BuyPass
//                .collect(Collectors.toList());
//    }
//
//    // Method to get the total number of passes purchased by all users
//    public int getTotalPassesPurchasedByAllUsers() {
//        List<BuyPass> allPurchases = buyPassRepo.findAll(); // Fetch all pass purchases
//        return allPurchases.size(); // Return the total number of passes purchased
//    }
//}

package com.carParkingNew.carParking.service;

import com.carParkingNew.carParking.entity.BuyPass;
import com.carParkingNew.carParking.entity.PackItem;
import com.carParkingNew.carParking.entity.User;
import com.carParkingNew.carParking.repository.BuyPassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyPassService {

    @Autowired
    private BuyPassRepo buyPassRepo;

    // Method to record a pass purchase
    public String buyPass(User user, PackItem packItem) {
        // Check if the user already has any pass
        Optional<BuyPass> existingPurchase = buyPassRepo.findByUser(user);

        if (existingPurchase.isPresent()) {
            throw new RuntimeException("User has already purchased a pass");
        } else {
            BuyPass newPurchase = new BuyPass(null, user, packItem);
            buyPassRepo.save(newPurchase); // Save the new purchase record
            return "Pass purchase successful"; // Return success message
        }
    }

    // Method to get the purchased pass for a specific user
    public Optional<BuyPass> getPurchasedPassByUser(User user) {
        return buyPassRepo.findByUser(user); // This will return an Optional<BuyPass>
    }

    // Method to get the total number of passes purchased by all users
    public int getTotalPassesPurchasedByAllUsers() {
        return (int) buyPassRepo.count(); // Count all passes purchased
    }
}
