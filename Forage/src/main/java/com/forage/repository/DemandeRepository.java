package com.forage.repository;

import com.forage.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    
    List<Demande> findByClientId(int clientId);
    
    List<Demande> findByDistrict(String district);
    
    @Query("SELECT d FROM Demande d LEFT JOIN FETCH d.client ORDER BY d.date DESC")
    List<Demande> findAllWithClient();
}