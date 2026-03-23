package com.forage.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.forage.entity.DemandeStatus;
import com.forage.repository.DemandeStatusRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DemandeStatusService {
    private final DemandeStatusRepository demandeStatusRepository;

    public List<DemandeStatus> findAll() {
        return demandeStatusRepository.findAll();
    }

    public DemandeStatus findById(int id) {
        return demandeStatusRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DemandeStatus non trouvé: " + id));
    }
 
}
