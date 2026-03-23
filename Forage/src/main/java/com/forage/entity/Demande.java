package com.forage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "demandes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "La date est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;
    
    @NotBlank(message = "Le lieu est obligatoire")
    @Column(nullable = false)
    private String lieu;
    
    @NotBlank(message = "Le district est obligatoire")
    @Column(nullable = false)
    private String district;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull(message = "Le client est obligatoire")
    private Client client;
    
    @OneToOne(mappedBy = "demande", cascade = CascadeType.ALL)
    private Devis devis;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    @OrderBy("date DESC")
    private List<DemandeStatus> historiqueStatus = new ArrayList<>();
    
    // Méthode utilitaire pour obtenir le dernier statut
    @Transient
    public DemandeStatus getDernierStatus() {
        return historiqueStatus.isEmpty() ? null : historiqueStatus.get(0);
    }
}