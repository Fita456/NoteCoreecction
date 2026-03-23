package com.forage.controller;

import com.forage.entity.Devis;
import com.forage.entity.DetailsDevis;
import com.forage.service.DemandeService;
import com.forage.service.DevisService;
import com.forage.repository.TypeDevisRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/devis")
@RequiredArgsConstructor
public class DevisController {
    
    private final DevisService devisService;
    private final DemandeService demandeService;
    private final TypeDevisRepository typeDevisRepository;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("devisList", devisService.findAll());
        return "devis/list";
    }
    
    @GetMapping("/new")
    public String createForm(@RequestParam(required = false) int demandeId, Model model) {
        Devis devis = new Devis();
        devis.setDate(LocalDate.now());
        
        if (demandeId != 0) {
            devis.setDemande(demandeService.findById(demandeId));
        }
        
        model.addAttribute("devis", devis);
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("typesDevis", typeDevisRepository.findAll());
        return "devis/form";
    }
    
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("devis", devisService.findById(id));
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("typesDevis", typeDevisRepository.findAll());
        return "devis/form";
    }
    
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Devis devis,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("demandes", demandeService.findAll());
            model.addAttribute("typesDevis", typeDevisRepository.findAll());
            return "devis/form";
        }
        
        devisService.save(devis);
        redirectAttributes.addFlashAttribute("success", "Devis enregistré avec succès");
        return "redirect:/devis";
    }
    
    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        Devis devis = devisService.findById(id);
        model.addAttribute("devis", devis);
        model.addAttribute("details", devisService.getDetails(id));
        model.addAttribute("newDetail", new DetailsDevis());
        return "devis/view";
    }
    
    @PostMapping("/{id}/details")
    public String addDetail(@PathVariable int id,
                            @Valid @ModelAttribute("newDetail") DetailsDevis detail,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Données invalides");
            return "redirect:/devis/" + id;
        }
        
        devisService.addDetail(id, detail);
        redirectAttributes.addFlashAttribute("success", "Détail ajouté");
        return "redirect:/devis/" + id;
    }
    
    @GetMapping("/{id}/details/{detailId}/delete")
    public String deleteDetail(@PathVariable int id,
                               @PathVariable int detailId,
                               RedirectAttributes redirectAttributes) {
        devisService.removeDetail(id, detailId);
        redirectAttributes.addFlashAttribute("success", "Détail supprimé");
        return "redirect:/devis/" + id;
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            devisService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Devis supprimé");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Impossible de supprimer");
        }
        return "redirect:/devis";
    }
}