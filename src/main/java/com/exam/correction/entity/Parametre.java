package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parametre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parametre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "comparateur_id", nullable = false)
    private Comparateur comparateur;
    
    @ManyToOne
    @JoinColumn(name = "resolution_id", nullable = false)
    private Resolution resolution;
    
    @Column(nullable = false)
    private Double seuil;
}