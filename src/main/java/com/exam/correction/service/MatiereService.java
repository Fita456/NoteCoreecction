package com.exam.correction.service;

import com.exam.correction.entity.Matiere;
import com.exam.correction.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {
    
    @Autowired
    private MatiereRepository matiereRepository;
    
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }
    
    public Optional<Matiere> getMatiereById(Long id) {
        return matiereRepository.findById(id);
    }
    
    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }
    
    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }
    
    public Optional<Matiere> getMatiereByName(String name) {
        return matiereRepository.findByName(name);
    }
}