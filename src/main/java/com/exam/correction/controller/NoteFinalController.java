package com.exam.correction.controller;

import com.exam.correction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/notes-final")
public class NoteFinalController {
    
    @Autowired
    private NoteFinalService noteFinalService;
    
    @Autowired
    private EtudiantService etudiantService;
    
    @Autowired
    private MatiereService matiereService;
    
    @GetMapping
    public String listNotesFinal(Model model) {
        model.addAttribute("notesFinal", noteFinalService.getAllNotesFinal());
        return "notesfinal/list";
    }
    
    @GetMapping("/calculer")
    public String showCalculerForm(Model model) {
        model.addAttribute("etudiants", etudiantService.getAllEtudiants());
        model.addAttribute("matieres", matiereService.getAllMatieres());
        return "notesfinal/calculer";
    }
    
    @PostMapping("/calculer")
    public String calculerNoteFinal(
            @RequestParam Long etudiantId,
            @RequestParam Long matiereId,
            RedirectAttributes redirectAttributes) {
        try {
            noteFinalService.calculerEtEnregistrerNoteFinal(etudiantId, matiereId);
            redirectAttributes.addFlashAttribute("success", "Note finale calculée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur: " + e.getMessage());
        }
        return "redirect:/notes-final";
    }
    
    @GetMapping("/details")
    public String showDetails(
            @RequestParam Long etudiantId,
            @RequestParam Long matiereId,
            Model model) {
        try {
            Map<String, Object> details = noteFinalService.getDetailsCalcul(etudiantId, matiereId);
            model.addAttribute("details", details);
            model.addAttribute("etudiant", etudiantService.getEtudiantById(etudiantId).orElse(null));
            model.addAttribute("matiere", matiereService.getMatiereById(matiereId).orElse(null));
            return "notesfinal/details";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "notesfinal/details";
        }
    }
    
    @GetMapping("/{id}/delete")
    public String deleteNoteFinal(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            noteFinalService.deleteNoteFinal(id);
            redirectAttributes.addFlashAttribute("success", "Note finale supprimée avec succès!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur: " + e.getMessage());
        }
        return "redirect:/notes-final";
    }
}