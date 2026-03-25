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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DemandeStatusService {
    
    private final DemandeStatusRepository demandeStatusRepository;
    private final DemandeRepository demandeRepository;
    private final StatusRepository statusRepository;
    
    public List<DemandeStatus> getHistorique(int demandeId) {
        return demandeStatusRepository.findByDemandeIdOrderByDateStatusDesc(demandeId);
    }
    
    public List<DemandeStatus> getHistoriqueWithDetails(int demandeId) {
        return demandeStatusRepository.findByDemandeWithDetails(demandeId);
    }
    
    public Optional<DemandeStatus> getDernierStatus(int demandeId) {
        return demandeStatusRepository.findFirstByDemandeIdOrderByDateStatusDesc(demandeId);
    }
    
    public DemandeStatus findById(int id) {
        return demandeStatusRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DemandeStatus non trouvé: " + id));
    }
    
    public DemandeStatus ajouterStatus(int demandeId, int statusId, String commentaire) {
        Demande demande = demandeRepository.findById(demandeId)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée: " + demandeId));
        
        Status status = statusRepository.findById(statusId)
            .orElseThrow(() -> new RuntimeException("Status non trouvé: " + statusId));
        
        DemandeStatus demandeStatus = new DemandeStatus();
        demandeStatus.setDemande(demande);
        demandeStatus.setStatus(status);
        demandeStatus.setDateStatus(LocalDateTime.now());
        demandeStatus.setCommentaire(commentaire);
        
        return demandeStatusRepository.save(demandeStatus);
    }
    
    public DemandeStatus creerStatusInitial(Demande demande, int statusId) {
        Status status = statusRepository.findById(statusId)
            .orElseThrow(() -> new RuntimeException("Status non trouvé: " + statusId));
        
        DemandeStatus demandeStatus = new DemandeStatus();
        demandeStatus.setDemande(demande);
        demandeStatus.setStatus(status);
        demandeStatus.setDateStatus(LocalDateTime.now());
        demandeStatus.setCommentaire("Statut initial");
        
        return demandeStatusRepository.save(demandeStatus);
    }
    
    public DemandeStatus creerStatusInitialParDefaut(Demande demande) {
        Status statusParDefaut = statusRepository.findAll()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Aucun status dans la base"));
        
        DemandeStatus demandeStatus = new DemandeStatus();
        demandeStatus.setDemande(demande);
        demandeStatus.setStatus(statusParDefaut);
        demandeStatus.setDateStatus(LocalDateTime.now());
        demandeStatus.setCommentaire("Création de la demande");
        
        return demandeStatusRepository.save(demandeStatus);
    }
    
    public void supprimer(int id) {
        demandeStatusRepository.deleteById(id);
    }

    public void supprimerHistorique(int demandeId) {
        demandeStatusRepository.deleteByDemandeId(demandeId);
    }
    
    public int nombreChangements(int demandeId) {
        return (int) demandeStatusRepository.countByDemandeId(demandeId);
    }
}