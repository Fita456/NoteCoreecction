package com.exam.correction.service;

import com.exam.correction.entity.Notes;
import com.exam.correction.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
    
    @Autowired
    private NotesRepository notesRepository;
    
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }
    
    public Optional<Notes> getNoteById(Long id) {
        return notesRepository.findById(id);
    }
    
    public Notes saveNote(Notes note) {
        return notesRepository.save(note);
    }
    
    public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }
    
    public List<Notes> getNotesByEtudiantAndMatiere(Long etudiantId, Long matiereId) {
        return notesRepository.findByEtudiantIdAndMatiereId(etudiantId, matiereId);
    }
}