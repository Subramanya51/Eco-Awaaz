package com.Eco_Awaaz.Eco_Awaaz.Service;

import com.Eco_Awaaz.Eco_Awaaz.Entity.Complaint_DetailsEntity;
import com.Eco_Awaaz.Eco_Awaaz.Repository.Complaint_DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {

    @Autowired
    private Complaint_DetailsRepository repository;

    public Map<String, Integer> getCountsByResource(String resourceType) {

        // ✅ Fetch only required data from DB
        List<Complaint_DetailsEntity> complaints =
                repository.findByResourceTypeIgnoreCase(resourceType);

        // ✅ Predefined categories (UI-friendly)
        Map<String, Integer> result = new LinkedHashMap<>();

        switch (resourceType.toUpperCase()) {

            case "WATER":
                result.put("No water supply", 0);
                result.put("Contaminated water", 0);
                result.put("Low water pressure", 0);
                result.put("Leaking pipe", 0);
                break;

            case "ELECTRICITY":
                result.put("No power supply", 0);
                result.put("Transformer or DC blast", 0);
                result.put("Street light not working", 0);
                result.put("Loose hanging wire", 0);
                break;

            case "WASTE":
                result.put("Garbage not collected", 0);
                result.put("Blocked or broken drainage", 0);
                result.put("Garbage dumped on roads", 0);
                result.put("Bad smell and disease risk", 0);
                break;

            default:
                throw new RuntimeException("Invalid resource type");
        }

        Map<String, Integer> normalized = new HashMap<>();

        for (String key : result.keySet()) {
            normalized.put(key.toLowerCase(), result.get(key));
        }

        for (Complaint_DetailsEntity c : complaints) {

            String formatted = c.getComplaintType()
                    .toLowerCase()
                    .replace("_", " ");

            if (normalized.containsKey(formatted)) {
                normalized.put(formatted, normalized.get(formatted) + 1);
            }
        }

// map back to original keys
        int i = 0;
        for (String key : result.keySet()) {
            result.put(key, normalized.get(key.toLowerCase()));
        }

        return result;
    }
}