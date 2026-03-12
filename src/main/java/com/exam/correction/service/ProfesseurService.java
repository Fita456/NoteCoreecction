package com.exam.correction.service;

import com.exam.correction.entity.Professeur;
import com.exam.correction.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurService {
    
    @Autowired
    private ProfesseurRepository professeurRepository;
    
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }
    
    public Optional<Professeur> getProfesseurById(Long id) {
        return professeurRepository.findById(id);
    }
    
    public Professeur saveProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }
    
    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }
}