package com.forage.repository;

import com.forage.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Integer> {
    
    @Query("SELECT d FROM Devis d " +
           "LEFT JOIN FETCH d.demande dem " +
           "LEFT JOIN FETCH dem.client " +
           "LEFT JOIN FETCH d.typeDevis " +
           "ORDER BY d.dateDevis DESC")
    List<Devis> findAllWithDetails();
    
    @Query("SELECT d FROM Devis d " +
           "LEFT JOIN FETCH d.demande dem " +
           "LEFT JOIN FETCH dem.client " +
           "LEFT JOIN FETCH d.typeDevis " +
           "LEFT JOIN FETCH d.details " +
           "WHERE d.id = :id")
    Optional<Devis> findByIdWithDetails(@Param("id") int id);
    
    Optional<Devis> findByDemandeId(int demandeId);
    
    boolean existsByDemandeId(int demandeId);
}