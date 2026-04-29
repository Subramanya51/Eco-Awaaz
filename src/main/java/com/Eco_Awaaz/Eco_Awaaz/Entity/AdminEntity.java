package com.Eco_Awaaz.Eco_Awaaz.Entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "admins")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminEntity
{

        @Id
        @Column(name = "admin_id", nullable = false, unique = true)
        private String adminId;  // manually entered (e.g., ADM001)

        @Column(name = "admin_name", nullable = false)
        private String adminName;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String role;  // ADMIN, SUPPORT, etc.
    }

