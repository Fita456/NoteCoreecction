package com.forage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "demande_status")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DemandeStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "La date est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_status", nullable = false)
    private LocalDateTime dateStatus;
    
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_id", nullable = false)
    @NotNull(message = "La demande est obligatoire")
    private Demande demande;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull(message = "Le statut est obligatoire")
    private Status status;
    
    // Date automatique avant insertion
    @PrePersist
    public void prePersist() {
        if (dateStatus == null) {
            dateStatus = LocalDateTime.now();
        }
    }
}