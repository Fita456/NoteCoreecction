package com.forage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.forage.entity.TypeDevis;
import com.forage.repository.TypeDevisRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import lombok.NoArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TypeDevisService {
    private final TypeDevisRepository typeDevisRepository;

        public TypeDevis findById(int id) {
            return typeDevisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypeDevis non trouvé: " + id));
        }

        public List<TypeDevis> findAll() {
            return typeDevisRepository.findAll();
        }
}
