package com.forage.repository;

import com.forage.entity.DemandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Integer> {
    List<DemandeStatus> findByDemandeIdOrderByDateDesc(int demandeId);
}