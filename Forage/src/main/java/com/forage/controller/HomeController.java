package com.forage.controller;

import com.forage.service.ClientService;
import com.forage.service.DemandeService;
import com.forage.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final ClientService clientService;
    private final DemandeService demandeService;
    private final DevisService devisService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("nbClients", clientService.findAll().size());
        model.addAttribute("nbDemandes", demandeService.findAll().size());
        model.addAttribute("nbDevis", devisService.findAll().size());
        return "home";
    }
}