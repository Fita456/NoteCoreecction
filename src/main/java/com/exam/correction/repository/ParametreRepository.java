package com.exam.correction.repository;

import com.exam.correction.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    Optional<Parametre> findByMatiereId(Long matiereId);
    List<Parametre> findAllByMatiereId(Long matiereId); // NOUVEAU : pour plusieurs paramètres
}