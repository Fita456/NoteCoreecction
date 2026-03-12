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
        INFERIEUR,              // < (inférieur à)
        SUPERIEUR,              // > (supérieur à)
        INFERIEUR_OU_EGAL,      // <= (inférieur ou égal à)
        SUPERIEUR_OU_EGAL       // >= (supérieur ou égal à)
    }
    
    // Méthode utilitaire pour obtenir le symbole
    public String getSymbole() {
        switch (ref) {
            case INFERIEUR:
                return "<";
            case SUPERIEUR:
                return ">";
            case INFERIEUR_OU_EGAL:
                return "<=";
            case SUPERIEUR_OU_EGAL:
                return ">=";
            default:
                return "";
        }
    }
    
    // Méthode utilitaire pour obtenir la description
    public String getDescription() {
        switch (ref) {
            case INFERIEUR:
                return "Inférieur à";
            case SUPERIEUR:
                return "Supérieur à";
            case INFERIEUR_OU_EGAL:
                return "Inférieur ou égal à";
            case SUPERIEUR_OU_EGAL:
                return "Supérieur ou égal à";
            default:
                return "";
        }
    }
}