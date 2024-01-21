package com.example.pfebackend.auth;

import com.example.pfebackend.models.Enumeration.Pays;
import com.example.pfebackend.models.Enumeration.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsUpdateRequest {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String nomEntreprise;

    private String password;
    private boolean etatDispo;
    private int numTel;
    @Enumerated(EnumType.STRING)
    private Pays pays;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profileImageUrl;
}
