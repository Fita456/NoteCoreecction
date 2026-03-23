package com.forage.repository;

import com.forage.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Integer> {
    
    Optional<Devis> findByDemandeId(int demandeId);
    
    @Query("SELECT d FROM Devis d " +
           "LEFT JOIN FETCH d.demande dem " +
           "LEFT JOIN FETCH dem.client " +
           "LEFT JOIN FETCH d.typeDevis")
    List<Devis> findAllWithDetails();
}