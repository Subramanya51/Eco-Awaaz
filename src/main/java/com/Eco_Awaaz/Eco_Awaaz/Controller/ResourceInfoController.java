//package com.Eco_Awaaz.Eco_Awaaz.Controller;
//
//import com.Eco_Awaaz.Eco_Awaaz.Entity.ResourceInfoEntity;
//import com.Eco_Awaaz.Eco_Awaaz.Service.ResourceInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/resource")
//public class ResourceInfoController {
//
//    @Autowired
//    private ResourceInfoService service;
//
//    // 🆕 Add / Update description
//    @PostMapping("/add")
//    public ResourceInfoEntity addResource(@RequestBody Map<String, String> body) {
//
//        return service.addOrUpdate(
//                body.get("resourceType"),
//                body.get("description")
//        );
//    }
//
//    // 📥 Get description
//    @GetMapping("/{type}")
//    public ResourceInfoEntity getResource(@PathVariable String type) {
//        return service.getByResourceType(type);
//    }
//}
package com.Eco_Awaaz.Eco_Awaaz.Controller;

import com.Eco_Awaaz.Eco_Awaaz.Entity.ResourceInfoEntity;
import com.Eco_Awaaz.Eco_Awaaz.Service.ResourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceInfoController {

    @Autowired
    private ResourceInfoService service;

    // 🆕 Add / Update (clean JSON)
    @PostMapping("/add")
    public ResourceInfoEntity addResource(@RequestBody ResourceInfoEntity resource) {
        return service.addOrUpdate(
                resource.getResourceType(),
                resource.getDescription()
        );
    }

    // 📥 Get by type (dynamic)
    @GetMapping("/{type}")
    public ResourceInfoEntity getResource(@PathVariable String type) {
        return service.getByResourceType(type);
    }
}