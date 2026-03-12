package com.exam.correction.repository;

import com.exam.correction.entity.NoteFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteFinalRepository extends JpaRepository<NoteFinal, Long> {
    List<NoteFinal> findByEtudiantId(Long etudiantId);
    List<NoteFinal> findByMatiereId(Long matiereId);
    Optional<NoteFinal> findByEtudiantIdAndMatiereIdAndParametreId(Long etudiantId, Long matiereId, Long parametreId);
}