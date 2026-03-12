package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "matiere")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    private List<Professeur> professeurs;
    
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    private List<Notes> notes;
    
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    private List<Parametre> parametres;
}