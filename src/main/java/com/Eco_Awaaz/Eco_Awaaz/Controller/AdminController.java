package com.Eco_Awaaz.Eco_Awaaz.Controller;

import com.Eco_Awaaz.Eco_Awaaz.Entity.AdminEntity;
import com.Eco_Awaaz.Eco_Awaaz.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/login")
    public AdminEntity login(@RequestBody AdminEntity admin) {

        return adminService.login(
                admin.getAdminId(),
                admin.getPassword()
        );
    }
    // 🆕 REGISTER ADMIN
    @PostMapping("/register")
    public AdminEntity register(@RequestBody Map<String, String> body) {

        AdminEntity admin = AdminEntity.builder()
                .adminId(body.get("adminId"))
                .adminName(body.get("adminName"))
                .password(body.get("password")) .role(body.get("role"))
                .build();

        return adminService.register(admin);
    }
}