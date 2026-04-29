//package com.Eco_Awaaz.Eco_Awaaz.Service;
//
//import com.Eco_Awaaz.Eco_Awaaz.Entity.ResourceInfoEntity;
//import com.Eco_Awaaz.Eco_Awaaz.Repository.ResourceInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ResourceInfoService {
//
//    @Autowired
//    private ResourceInfoRepository repository;
//
//    // 🆕 Add or Update description
//    public ResourceInfoEntity addOrUpdate(String resourceType, String description) {
//
//        return repository.findByResourceType(resourceType)
//                .map(existing -> {
//                    existing.setDescription(description);
//                    return repository.save(existing);
//                })
//                .orElseGet(() -> repository.save(
//                        ResourceInfoEntity.builder()
//                                .resourceType(resourceType)
//                                .description(description)
//                                .build()
//                ));
//    }
//
//    // 📥 Get description
//    public ResourceInfoEntity getByResourceType(String resourceType) {
//        return repository.findByResourceType(resourceType)
//                .orElseThrow(() -> new RuntimeException("Resource not found"));
//    }
//}
package com.Eco_Awaaz.Eco_Awaaz.Service;

import com.Eco_Awaaz.Eco_Awaaz.Entity.ResourceInfoEntity;
import com.Eco_Awaaz.Eco_Awaaz.Repository.ResourceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResourceInfoService {

    @Autowired
    private ResourceInfoRepository repository;

    // 🔁 Add or Update
    public ResourceInfoEntity addOrUpdate(String type, String description) {

        ResourceInfoEntity resource = repository
                .findByResourceTypeIgnoreCase(type)
                .orElse(new ResourceInfoEntity());

        resource.setResourceType(type.toLowerCase());
        resource.setDescription(description);

        return repository.save(resource);
    }

    // 📥 Get by type
    public ResourceInfoEntity getByResourceType(String type) {

        return repository
                .findByResourceTypeIgnoreCase(type)
                .orElseThrow(() ->
                        new RuntimeException("No data found for: " + type));
    }
    public ResourceInfoEntity add(String type, String description) {

        ResourceInfoEntity resource = ResourceInfoEntity.builder()
                .resourceType(type.toLowerCase())
                .description(description)
                .createdAt(LocalDateTime.now())   // ✅ set time
                .build();

        return repository.save(resource);
    }


    // ✅ THIS IS YOUR METHOD (add here)
    public ResourceInfoEntity getLatest(String type) {

        return repository
                .findTopByResourceTypeIgnoreCaseOrderByCreatedAtDesc(type)
                .orElseThrow(() ->
                        new RuntimeException("No data found for: " + type));
    }
}