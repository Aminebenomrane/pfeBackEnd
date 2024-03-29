package com.example.pfebackend.models;


import com.example.pfebackend.models.Enumeration.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String place;
    private String description;
    @Enumerated(EnumType.STRING)
    private DomaineExpertise domaineExpertise;

    @Enumerated(EnumType.STRING)
    private Technologie technologie;
    private String imageUrl;
    private float budget;
    private int duree;
    @Enumerated(EnumType.STRING)
    private StatusProjet statusProjet;
    private int nombreDePostesVacants;
    @Enumerated(EnumType.STRING)
    private Experience experience;
    private LocalDate postDate;
    @Enumerated(EnumType.STRING)
    private NatureTravail natureDuTravail;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "projet_images",
            joinColumns = {
                    @JoinColumn(name = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }
    )
    private Set<ImageModel> productImages;

    public Set<ImageModel> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageModel> productImages) {
        this.productImages = productImages;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "projet")
    private List<DemandeRealisation> DemandeRealisation;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idEntreprise")
    private User entreprise;
}
