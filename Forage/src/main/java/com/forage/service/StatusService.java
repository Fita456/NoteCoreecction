package com.forage.service;

import com.forage.repository.StatusRepository;
import com.forage.entity.Status;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusService {
    private final StatusRepository statusRepository;

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));
    }


}
