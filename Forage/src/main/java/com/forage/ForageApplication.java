package com.forage;

import com.forage.entity.*;
import com.forage.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForageApplication.class, args);
    }
    
    // Données initiales
    @Bean
    CommandLineRunner initData(StatusRepository statusRepo, 
                               TypeDevisRepository typeDevisRepo) {
        return args -> {
            // Status par défaut
            if (statusRepo.count() == 0) {
                statusRepo.save(new Status(null, "En attente", null));
                statusRepo.save(new Status(null, "En cours", null));
                statusRepo.save(new Status(null, "Validé", null));
                statusRepo.save(new Status(null, "Rejeté", null));
                statusRepo.save(new Status(null, "Terminé", null));
            }
            
            // Types de devis par défaut
            if (typeDevisRepo.count() == 0) {
                typeDevisRepo.save(new TypeDevis(null, "Forage simple", null));
                typeDevisRepo.save(new TypeDevis(null, "Forage profond", null));
                typeDevisRepo.save(new TypeDevis(null, "Réhabilitation", null));
            }
        };
    }
}