package com.exam.correction.controller;

import com.exam.correction.entity.Etudiant;
import com.exam.correction.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {
    
    @Autowired
    private EtudiantService etudiantService;
    
    @GetMapping
    public String listEtudiants(Model model) {
        model.addAttribute("etudiants", etudiantService.getAllEtudiants());
        return "etudiant/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        model.addAttribute("action", "create");
        return "etudiant/form";
    }
    
    @PostMapping
    public String createEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantService.saveEtudiant(etudiant);
        return "redirect:/etudiants";
    }
    
    @GetMapping("/{id}")
    public String viewEtudiant(@PathVariable Long id, Model model) {
        etudiantService.getEtudiantById(id).ifPresent(etudiant -> 
            model.addAttribute("etudiant", etudiant)
        );
        return "etudiant/view";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        etudiantService.getEtudiantById(id).ifPresent(etudiant -> {
            model.addAttribute("etudiant", etudiant);
            model.addAttribute("action", "edit");
        });
        return "etudiant/form";
    }
    
    @PostMapping("/{id}")
    public String updateEtudiant(@PathVariable Long id, @ModelAttribute Etudiant etudiant) {
        etudiant.setId(id);
        etudiantService.saveEtudiant(etudiant);
        return "redirect:/etudiants";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return "redirect:/etudiants";
    }
}