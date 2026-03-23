package com.forage.service;

import com.forage.entity.Demande;
import com.forage.entity.DemandeStatus;
import com.forage.entity.Status;
import com.forage.repository.DemandeRepository;
import com.forage.repository.DemandeStatusRepository;
import com.forage.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DemandeService {
    
    private final DemandeRepository demandeRepository;
    private final DemandeStatusRepository demandeStatusRepository;
    private final StatusRepository statusRepository;
    
    public List<Demande> findAll() {
        return demandeRepository.findAllWithClient();
    }

    public List<Demande> findByClientId(Long clientId) {
        return demandeRepository.findByClientId(clientId);
    }
    
    public Demande findById(Long id) {
        return demandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée: " + id));
    }
    
    public Demande save(Demande demande) {
        return demandeRepository.save(demande);
    }
    
    public Demande saveWithInitialStatus(Demande demande, Long statusId) {
        Demande savedDemande = demandeRepository.save(demande);
        
        if (statusId != null) {
            Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status non trouvé"));
            
            DemandeStatus demandeStatus = new DemandeStatus();
            demandeStatus.setDemande(savedDemande);
            demandeStatus.setStatus(status);
            demandeStatus.setDate(LocalDateTime.now());
            demandeStatusRepository.save(demandeStatus);
        }
        
        return savedDemande;
    }
    
    public void deleteById(Long id) {
        demandeRepository.deleteById(id);
    }
    
    public void changerStatus(Long demandeId, Long statusId, String commentaire) {
        Demande demande = findById(demandeId);
        Status status = statusRepository.findById(statusId)
            .orElseThrow(() -> new RuntimeException("Status non trouvé"));
        
        DemandeStatus demandeStatus = new DemandeStatus();
        demandeStatus.setDemande(demande);
        demandeStatus.setStatus(status);
        demandeStatus.setDate(LocalDateTime.now());
        demandeStatus.setCommentaire(commentaire);
        
        demandeStatusRepository.save(demandeStatus);
    }
    
    public List<DemandeStatus> getHistoriqueStatus(Long demandeId) {
        return demandeStatusRepository.findByDemandeIdOrderByDateDesc(demandeId);
    }
}