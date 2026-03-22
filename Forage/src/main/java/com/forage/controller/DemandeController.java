package com.forage.controller;

import com.forage.entity.Demande;
import com.forage.service.ClientService;
import com.forage.service.DemandeService;
import com.forage.repository.StatusRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/demandes")
@RequiredArgsConstructor
public class DemandeController {
    
    private final DemandeService demandeService;
    private final ClientService clientService;
    private final StatusRepository statusRepository;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        return "demande/list";
    }
    
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        return "demande/form";
    }
    
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("demande", demandeService.findById(id));
        model.addAttribute("clients", clientService.findAll());
        return "demande/form";
    }
    
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Demande demande,
                       BindingResult result,
                       @RequestParam(required = false) Long statusId,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("clients", clientService.findAll());
            model.addAttribute("statuses", statusRepository.findAll());
            return "demande/form";
        }
        
        if (demande.getId() == null) {
            demandeService.saveWithInitialStatus(demande, statusId);
        } else {
            demandeService.save(demande);
        }
        
        redirectAttributes.addFlashAttribute("success", "Demande enregistrée avec succès");
        return "redirect:/demandes";
    }
    
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Demande demande = demandeService.findById(id);
        model.addAttribute("demande", demande);
        model.addAttribute("historiqueStatus", demandeService.getHistoriqueStatus(id));
        model.addAttribute("statuses", statusRepository.findAll());
        return "demande/view";
    }
    
    @PostMapping("/{id}/status")
    public String changerStatus(@PathVariable Long id,
                                @RequestParam Long statusId,
                                @RequestParam(required = false) String commentaire,
                                RedirectAttributes redirectAttributes) {
        demandeService.changerStatus(id, statusId, commentaire);
        redirectAttributes.addFlashAttribute("success", "Statut mis à jour");
        return "redirect:/demandes/" + id;
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            demandeService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Demande supprimée");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Impossible de supprimer");
        }
        return "redirect:/demandes";
    }
}