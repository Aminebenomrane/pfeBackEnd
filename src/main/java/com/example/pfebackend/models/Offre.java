package com.example.pfebackend.models;


import com.example.pfebackend.models.Enumeration.DomaineExpertise;
import com.example.pfebackend.models.Enumeration.Experience;
import com.example.pfebackend.models.Enumeration.NatureTravail;
import com.example.pfebackend.models.Enumeration.Technologie;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private float prix_heure;


    @Enumerated(EnumType.STRING)
    private DomaineExpertise domaineExpertise;

    @Enumerated(EnumType.STRING)
    private Technologie technologie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_freelance")
    private User freelancer;
    @JsonIgnore
    @OneToMany(mappedBy = "offre")
    private List<DemandeRecrutement> demandeRecrutements;
    @Enumerated(EnumType.STRING)
    private Experience experience;
    private LocalDate postDate;
    @Enumerated(EnumType.STRING)
    private NatureTravail natureDuTravail;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "projet_imagess",
            joinColumns = {
                    @JoinColumn(name = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }
    )
    private Set<ImageModel> productImagess;

    public Set<ImageModel> getProductImagess() {
        return productImagess;
    }
}
