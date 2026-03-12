package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "note_final")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteFinal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;
    
    @ManyToOne
    @JoinColumn(name = "parametre_id", nullable = false)
    private Parametre parametre;
    
    @Column(name = "note_final", nullable = false)
    private Double noteFinal;
    
    @Column(name = "date_entre")
    private LocalDateTime dateEntre;
    
    @PrePersist
    public void prePersist() {
        if (dateEntre == null) {
            dateEntre = LocalDateTime.now();
        }
    }
}