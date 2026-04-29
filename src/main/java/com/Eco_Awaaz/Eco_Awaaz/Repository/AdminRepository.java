package com.Eco_Awaaz.Eco_Awaaz.Repository;
import com.Eco_Awaaz.Eco_Awaaz.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    // 🔍 Find admin by ID
    Optional<AdminEntity> findByAdminId(String adminId);

    // 🔐 For login (hackathon simple)
    Optional<AdminEntity> findByAdminIdAndPassword(String adminId, String password);
}