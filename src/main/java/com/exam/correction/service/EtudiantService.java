package com.exam.correction.service;

import com.exam.correction.entity.Etudiant;
import com.exam.correction.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
    
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }
    
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
    
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
    
    public List<Etudiant> searchEtudiantsByNom(String nom) {
        return etudiantRepository.findByNomContainingIgnoreCase(nom);
    }
}