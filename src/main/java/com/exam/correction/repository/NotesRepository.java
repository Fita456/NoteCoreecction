package com.exam.correction.repository;

import com.exam.correction.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByEtudiantIdAndMatiereId(Long etudiantId, Long matiereId);
}