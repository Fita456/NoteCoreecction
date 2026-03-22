package com.forage.service;

import com.forage.entity.Devis;
import com.forage.entity.DetailsDevis;
import com.forage.repository.DevisRepository;
import com.forage.repository.DetailsDevisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DevisService {
    
    private final DevisRepository devisRepository;
    private final DetailsDevisRepository detailsDevisRepository;
    
    public List<Devis> findAll() {
        return devisRepository.findAllWithDetails();
    }
    
    public Devis findById(Long id) {
        return devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouvé: " + id));
    }
    
    public Devis save(Devis devis) {
        return devisRepository.save(devis);
    }
    
    public void deleteById(Long id) {
        devisRepository.deleteById(id);
    }
    
    public DetailsDevis addDetail(Long devisId, DetailsDevis detail) {
        Devis devis = findById(devisId);
        devis.addDetail(detail);
        devisRepository.save(devis);
        return detail;
    }
    
    public void removeDetail(Long devisId, Long detailId) {
        Devis devis = findById(devisId);
        DetailsDevis detail = detailsDevisRepository.findById(detailId)
            .orElseThrow(() -> new RuntimeException("Détail non trouvé"));
        devis.removeDetail(detail);
        devisRepository.save(devis);
    }
    
    public List<DetailsDevis> getDetails(Long devisId) {
        return detailsDevisRepository.findByDevisId(devisId);
    }
}