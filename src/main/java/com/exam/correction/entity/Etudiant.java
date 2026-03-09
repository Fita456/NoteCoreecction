package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "etudiant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(name = "date_naissance")
    private LocalDate dateNaissance;
    
    @Column(name = "etablissement_origine")
    private String etablissementOrigine;
    
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Notes> notes;
    
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<NoteFinal> notesFinal;
}