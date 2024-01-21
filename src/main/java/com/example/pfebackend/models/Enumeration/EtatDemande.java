package com.example.pfebackend.models.Enumeration;


public enum EtatDemande {
    En_Cours("En Cours"),
    Valider("Valider"),
    Annuler("Annuler");
    private final String label;

    EtatDemande(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
