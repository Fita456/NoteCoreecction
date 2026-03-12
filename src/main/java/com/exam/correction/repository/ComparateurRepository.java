package com.exam.correction.repository;

import com.exam.correction.entity.Comparateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComparateurRepository extends JpaRepository<Comparateur, Long> {
}