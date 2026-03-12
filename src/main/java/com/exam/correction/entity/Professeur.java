package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "professeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professeur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;
    
    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private List<Notes> notes;
}