package com.example.pfebackend.models;


import com.example.pfebackend.config.Encrypt;
import com.example.pfebackend.models.Enumeration.Pays;
import com.example.pfebackend.models.Enumeration.Role;
import com.example.pfebackend.token.Token;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")

public class User implements UserDetails {

    @Id
    @GeneratedValue
    private int id;
    @Convert(converter = Encrypt.class)

    private String firstname;
    @Convert(converter = Encrypt.class)

    private String lastname;
    @Convert(converter = Encrypt.class)

    private String email;

    private String nomEntreprise;

    private String password;
    private String siteweb;
    private boolean etatDispo;
    private int numTel;
    @Enumerated(EnumType.STRING)
    private Pays pays;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profileImageUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @JsonIgnore
    @OneToMany(mappedBy = "entreprise")
    private List<DemandeRecrutement> demandeRecrutementsEn;
    @JsonIgnore
    @OneToMany(mappedBy = "freelancer")
    private List<DemandeRealisation> demandeRealisationFr;
    @JsonIgnore
    @OneToMany(mappedBy = "freelancer")
    private List<Offre> offreList;

    @JsonIgnore
    @OneToMany(mappedBy = "entreprise")
    private List<Projet> listeDesProjets;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "freelancer_competence",
            joinColumns = @JoinColumn(name = "freelancer_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competences;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null) {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }
        return Collections.emptyList();
    }
    @JsonIgnore
    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    private List<Message> sentMessages;
    @JsonIgnore

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Ticket> createdTickets;
    @JsonIgnore

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    private List<Ticket> assignedTickets;
    @JsonIgnore
    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
    private List<Message> receivedMessages;
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
