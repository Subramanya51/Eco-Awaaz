//package com.Eco_Awaaz.Eco_Awaaz.Service;
//
//import com.Eco_Awaaz.Eco_Awaaz.Entity.Complaint_DetailsEntity;
//import com.Eco_Awaaz.Eco_Awaaz.Repository.Complaint_DetailsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class DashboardService {
//
//    @Autowired
//    private Complaint_DetailsRepository repository;
//
//    public Map<String, Object> getDashboardByResource(String resourceType) {
//
//        List<Complaint_DetailsEntity> complaints =
//                repository.findByResourceType(resourceType);
//
//        if (complaints.isEmpty()) {
//            throw new RuntimeException("No " + resourceType + " complaints found");
//        }
//
//        Map<String, Integer> countMap = new HashMap<>();
//
//        for (Complaint_DetailsEntity c : complaints) {
//            String postalCode = c.getPostalCode();
//
//            countMap.put(
//                    postalCode,
//                    countMap.getOrDefault(postalCode, 0) + 1
//            );
//        }
//
//        String topPostalCode = null;
//        int maxCount = 0;
//
//        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//            if (entry.getValue() > maxCount) {
//                maxCount = entry.getValue();
//                topPostalCode = entry.getKey();
//            }
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("postalCode", topPostalCode);
//        response.put("complaintCount", maxCount);
//        response.put("resourceType", resourceType);
//
//        return response;
//    }
//}
package com.Eco_Awaaz.Eco_Awaaz.Service;

import com.Eco_Awaaz.Eco_Awaaz.Entity.Complaint_DetailsEntity;
import com.Eco_Awaaz.Eco_Awaaz.Repository.Complaint_DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private Complaint_DetailsRepository complaintRepository;

    // 🔥 Common method for all resource types
    public Map<String, Object> getDashboardByResource(String resourceType) {

        List<Complaint_DetailsEntity> list = complaintRepository.findAll();

        Map<String, Integer> postalCount = new HashMap<>();
        Map<String, Integer> typeCount = new HashMap<>();

        for (Complaint_DetailsEntity c : list) {

            if (resourceType.equalsIgnoreCase(c.getResourceType())) {

                postalCount.put(
                        c.getPostalCode(),
                        postalCount.getOrDefault(c.getPostalCode(), 0) + 1
                );

                typeCount.put(
                        c.getComplaintType(),
                        typeCount.getOrDefault(c.getComplaintType(), 0) + 1
                );
            }
        }

        String topPostal = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : postalCount.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topPostal = entry.getKey();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("resourceType", resourceType);
        response.put("topPostalCode", topPostal);
        response.put("totalComplaints", max);
        response.put("complaintTypes", typeCount);

        return response;
    }
}