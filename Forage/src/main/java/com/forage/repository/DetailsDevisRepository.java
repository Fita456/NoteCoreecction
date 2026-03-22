package com.forage.repository;

import com.forage.entity.DetailsDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailsDevisRepository extends JpaRepository<DetailsDevis, Long> {
    List<DetailsDevis> findByDevisId(Long devisId);
}