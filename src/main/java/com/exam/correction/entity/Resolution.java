package com.exam.correction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resolution")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resolution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeResolution ref;
    
    public enum TypeResolution {
        MIN, MAX, AVERAGE
    }
}