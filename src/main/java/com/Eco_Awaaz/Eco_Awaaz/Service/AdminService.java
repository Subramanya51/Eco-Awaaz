package com.Eco_Awaaz.Eco_Awaaz.Service;


import com.Eco_Awaaz.Eco_Awaaz.Entity.AdminEntity;
import com.Eco_Awaaz.Eco_Awaaz.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // 🔐 Login logic
    public AdminEntity login(String adminId, String password) {

        Optional<AdminEntity> adminOptional =
                adminRepository.findByAdminIdAndPassword(adminId, password);

        if (adminOptional.isPresent()) {
            return adminOptional.get();
        } else {
            throw new RuntimeException("Invalid Admin ID or Password");
        }
    }
    public AdminEntity register(AdminEntity admin) {

        if (admin.getAdminId() == null || admin.getAdminId().isEmpty()) {
            throw new RuntimeException("Admin ID is required");
        }

        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        if (admin.getRole() == null || admin.getRole().isEmpty()) {
            throw new RuntimeException("Role is required");
        }

        if (adminRepository.findByAdminId(admin.getAdminId()).isPresent()) {
            throw new RuntimeException("Admin ID already exists");
        }

        return adminRepository.save(admin);
    }
}