package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comparateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comparateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeComparateur ref;
    
    public enum TypeComparateur {
        INFERIEUR, // <
        SUPERIEUR  // >
    }
}