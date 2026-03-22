package com.forage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devis")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Devis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "La date est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;
    
    @PositiveOrZero(message = "Le montant doit être positif")
    @Column(name = "montant_total", precision = 15, scale = 2)
    private BigDecimal montantTotal = BigDecimal.ZERO;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_devis_id", nullable = false)
    @NotNull(message = "Le type de devis est obligatoire")
    private TypeDevis typeDevis;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_id", nullable = false, unique = true)
    @NotNull(message = "La demande est obligatoire")
    private Demande demande;
    
    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailsDevis> details = new ArrayList<>();
    
    // Méthode pour recalculer le montant total
    public void calculerMontantTotal() {
        this.montantTotal = details.stream()
            .map(DetailsDevis::getMontant)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Méthode utilitaire pour ajouter un détail
    public void addDetail(DetailsDevis detail) {
        details.add(detail);
        detail.setDevis(this);
        calculerMontantTotal();
    }
    
    // Méthode utilitaire pour supprimer un détail
    public void removeDetail(DetailsDevis detail) {
        details.remove(detail);
        detail.setDevis(null);
        calculerMontantTotal();
    }
}