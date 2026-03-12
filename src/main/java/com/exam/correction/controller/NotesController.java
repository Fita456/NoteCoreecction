package com.exam.correction.controller;

import com.exam.correction.entity.Notes;
import com.exam.correction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NotesController {
    
    @Autowired
    private NotesService notesService;
    
    @Autowired
    private EtudiantService etudiantService;
    
    @Autowired
    private MatiereService matiereService;
    
    @Autowired
    private ProfesseurService professeurService;
    
    @GetMapping
    public String listNotes(Model model) {
        model.addAttribute("notes", notesService.getAllNotes());
        return "notes/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Notes());
        model.addAttribute("etudiants", etudiantService.getAllEtudiants());
        model.addAttribute("matieres", matiereService.getAllMatieres());
        model.addAttribute("professeurs", professeurService.getAllProfesseurs());
        model.addAttribute("action", "create");
        return "notes/form";
    }
    
    @PostMapping
    public String createNote(@ModelAttribute Notes note) {
        notesService.saveNote(note);
        return "redirect:/notes";
    }
    
    @GetMapping("/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        notesService.getNoteById(id).ifPresent(note -> 
            model.addAttribute("note", note)
        );
        return "notes/view";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        notesService.getNoteById(id).ifPresent(note -> {
            model.addAttribute("note", note);
            model.addAttribute("etudiants", etudiantService.getAllEtudiants());
            model.addAttribute("matieres", matiereService.getAllMatieres());
            model.addAttribute("professeurs", professeurService.getAllProfesseurs());
            model.addAttribute("action", "edit");
        });
        return "notes/form";
    }
    
    @PostMapping("/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute Notes note) {
        note.setId(id);
        notesService.saveNote(note);
        return "redirect:/notes";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
        return "redirect:/notes";
    }
}