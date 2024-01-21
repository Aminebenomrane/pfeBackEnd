package com.example.pfebackend.auth;


import com.example.pfebackend.models.Enumeration.Pays;
import com.example.pfebackend.models.Enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int numTel;
    private Pays pays;
    private String nomEntreprise;
    private String siteweb;
    private Role role;
}