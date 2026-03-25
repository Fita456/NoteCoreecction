package com.forage.controller;

import com.forage.entity.Demande;
import com.forage.service.ClientService;
import com.forage.service.DemandeService;
import com.forage.service.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/demandes")
@RequiredArgsConstructor
public class DemandeController {
    
    private final DemandeService demandeService;
    private final ClientService clientService;
    private final StatusService statusService;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        return "demande/list";
    }
    
    @GetMapping("/new")
    public String createForm(Model model) {
        Demande demande = new Demande();
        demande.setDate(LocalDate.now());
        
        model.addAttribute("demande", demande);
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "demande/form";
    }
    
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Demande demande,
                       BindingResult result,
                       @RequestParam(value = "statusId", required = false) Integer statusId,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("clients", clientService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            return "demande/form";
        }
        
        // Nouvelle demande (id == 0 car int primitif)
        if (demande.getId() == 0) {
            // ✅ Vérifier si statusId est null AVANT de l'utiliser
            if (statusId != null) {
                demandeService.creerAvecStatusInitial(demande, statusId);
            } else {
                // Créer sans statut ou avec statut par défaut
                demandeService.creerAvecStatusParDefaut(demande);
            }
            redirectAttributes.addFlashAttribute("success", "Demande créée avec succès");
        } else {
            demandeService.save(demande);
            redirectAttributes.addFlashAttribute("success", "Demande mise à jour");
        }
        
        return "redirect:/demandes";
    }
    
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("demande", demandeService.findByIdSimple(id));
        model.addAttribute("clients", clientService.findAll());
        return "demande/form";
    }
    
    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("demande", demandeService.findById(id));
        model.addAttribute("historiqueStatus", demandeService.getHistoriqueStatus(id));
        model.addAttribute("statuses", statusService.findAll());
        return "demande/view";
    }
    
    @PostMapping("/{id}/status")
    public String ajouterStatus(@PathVariable int id,
                                @RequestParam(value = "statusId", required = false) Integer statusId,
                                @RequestParam(value = "commentaire", required = false) String commentaire,
                                RedirectAttributes redirectAttributes) {
        
        // ✅ Vérifier si statusId est null
        if (statusId == null) {
            redirectAttributes.addFlashAttribute("error", "Veuillez sélectionner un statut");
            return "redirect:/demandes/" + id;
        }
        
        demandeService.changerStatus(id, statusId, commentaire);
        redirectAttributes.addFlashAttribute("success", "Statut ajouté");
        return "redirect:/demandes/" + id;
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            demandeService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Demande supprimée");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Impossible de supprimer");
        }
        return "redirect:/demandes";
    }
}