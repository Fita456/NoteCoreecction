package com.forage.repository;

import com.forage.entity.DemandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Integer> {
    
    // Historique par demande (ordre décroissant)
    List<DemandeStatus> findByDemandeIdOrderByDateStatusDesc(Integer demandeId);
    
    // Dernier statut d'une demande
    Optional<DemandeStatus> findFirstByDemandeIdOrderByDateStatusDesc(Integer demandeId);
    
    // Avec jointures
    @Query("SELECT ds FROM DemandeStatus ds " +
           "LEFT JOIN FETCH ds.status " +
           "LEFT JOIN FETCH ds.demande " +
           "WHERE ds.demande.id = :demandeId " +
           "ORDER BY ds.dateStatus DESC")
    List<DemandeStatus> findByDemandeWithDetails(@Param("demandeId") Integer demandeId);
    
    // Compter les statuts d'une demande
    long countByDemandeId(Integer demandeId);
    
    // Supprimer tout l'historique d'une demande
    void deleteByDemandeId(Integer demandeId);
}