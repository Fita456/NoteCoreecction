-- ============================================
-- DONNÉES INITIALES - PostgreSQL
-- ============================================
\c forage;

-- ============================================
-- STATUS
-- ============================================
INSERT INTO status (libelle) VALUES 
    ('Cree'),
    ('Devis_Etude_Cree'),
    ('Devis_Forage_Cree');

-- ============================================
-- TYPES DE DEVIS
-- ============================================
INSERT INTO types_devis (libelle) VALUES 
    ('Forage'),
    ('Etude');

-- ============================================
-- CLIENTS
-- ============================================
INSERT INTO clients (nom, contact) VALUES 
    ('Commune Rurale Ambohimanga', '034 12 345 67'),
    ('ONG Eau Pour Tous', 'contact@eaupourtous.mg'),
    ('Société JIRAMA', '020 22 123 45'),
    ('Association Villageoise Antsirabe', '033 98 765 43'),
    ('Ministère de l''Eau', 'info@mineau.gov.mg'),
    ('Commune Urbaine Fianarantsoa', '034 55 666 77'),
    ('ONG Aide Humanitaire', 'aide@humanitaire.org'),
    ('Hôpital Régional Toamasina', '020 53 321 00'),
    ('École Primaire Publique Ankazobe', '033 11 222 33'),
    ('Coopérative Agricole Betafo', '034 44 555 66');

-- ============================================
-- DEMANDES
-- ============================================
INSERT INTO demandes (date_demande, lieu, district, client_id) VALUES 
    ('2024-01-10', 'Fokontany Ambohitraivo', 'Antananarivo Atsimondrano', 1),
    ('2024-01-15', 'Village Mahazoarivo', 'Antsirabe II', 4),
    ('2024-02-01', 'Quartier Isotry', 'Antananarivo Renivohitra', 3),
    ('2024-02-10', 'Commune Ambatolampy', 'Ambatolampy', 2),
    ('2024-02-15', 'Zone Rurale Betafo', 'Betafo', 10),
    ('2024-02-20', 'Fokontany Ambalavao', 'Fianarantsoa I', 6),
    ('2024-02-25', 'Village Moramanga', 'Moramanga', 7),
    ('2024-03-01', 'Hôpital Central', 'Toamasina I', 8),
    ('2024-03-05', 'École EPP Ankazobe', 'Ankazobe', 9),
    ('2024-03-10', 'Siège Ministère', 'Antananarivo Renivohitra', 5);

-- ============================================
-- DEMANDE STATUS (Historique)
-- ============================================
INSERT INTO demande_status (date_status, demande_id, status_id, commentaire) VALUES 
    -- Demande 1 : Terminée
    ('2024-01-10 08:00:00', 1, 1, 'Demande Cree'),
    ('2024-01-12 09:30:00', 1, 2, 'Devis Etude'),
    ('2024-01-18 14:00:00', 1, 3, 'Devis Forage'),
    
    -- Demande 2 : Terminée
    ('2024-01-15 09:00:00', 2, 1, 'Demande Cree'),
    ('2024-01-20 11:00:00', 2, 2, 'Devis Etude'),
    ('2024-01-25 15:30:00', 2, 3, 'Devis Forage'),
    
    -- Demande 3 : En cours
    ('2024-02-01 10:00:00', 3, 1, 'Demande Cree'),
    ('2024-02-03 14:00:00', 3, 2, 'Devis Etude'),
    ('2024-02-08 11:00:00', 3, 3, 'Devis Forage'),
    
    -- Demande 4 : Devis envoyé
    ('2024-02-10 08:30:00', 4, 1, 'Demande Cree'),
    ('2024-02-14 10:00:00', 4, 2, 'Devis Etude'),
    ('2024-02-20 15:00:00', 4, 3, 'Devis Forage'),
    
    -- Demande 5 : En étude
    ('2024-02-15 09:00:00', 5, 1, 'Demande Cree'),
    ('2024-02-18 11:30:00', 5, 2, 'Devis Etude'),
    
    -- Demandes 6-10 : En attente
    ('2024-02-20 14:00:00', 6, 1, 'Demande Cree'),
    ('2024-02-25 10:00:00', 7, 1, 'Demande Cree'),
    ('2024-03-01 08:00:00', 8, 1, 'Demande Cree');

-- ============================================
-- DEVIS
-- ============================================
INSERT INTO devis (date_devis, montant_total, type_devis_id, demande_id) VALUES 
    ('2024-01-18', 15500000.00, 1, 1),
    ('2024-01-25', 28000000.00, 2, 2),
    ('2024-02-08', 45000000.00, 3, 3),
    ('2024-02-20', 22000000.00, 2, 4);

-- ============================================
-- DETAILS DEVIS
-- ============================================
-- Devis 1
INSERT INTO details_devis (libelle, prix_unitaire, quantite, devis_id) VALUES 
    ('Étude géophysique', 2000000.00, 1, 1),
    ('Forage 25m', 8000000.00, 1, 1),
    ('Tubage PVC', 1500000.00, 1, 1),
    ('Pompe manuelle India Mark II', 2500000.00, 1, 1),
    ('Main d''œuvre', 1500000.00, 1, 1);

-- Devis 2
INSERT INTO details_devis (libelle, prix_unitaire, quantite, devis_id) VALUES 
    ('Étude géophysique approfondie', 3000000.00, 1, 2),
    ('Forage 45m', 15000000.00, 1, 2),
    ('Tubage acier inox', 3000000.00, 1, 2),
    ('Pompe immergée', 4000000.00, 1, 2),
    ('Installation électrique', 2000000.00, 1, 2),
    ('Main d''œuvre', 1000000.00, 1, 2);

-- Devis 3
INSERT INTO details_devis (libelle, prix_unitaire, quantite, devis_id) VALUES 
    ('Étude hydrogéologique', 5000000.00, 1, 3),
    ('Forage 80m', 25000000.00, 1, 3),
    ('Tubage acier renforcé', 5000000.00, 1, 3),
    ('Pompe industrielle', 6000000.00, 1, 3),
    ('Système de filtration', 2000000.00, 1, 3),
    ('Main d''œuvre spécialisée', 2000000.00, 1, 3);

-- Devis 4
INSERT INTO details_devis (libelle, prix_unitaire, quantite, devis_id) VALUES 
    ('Étude de site', 2500000.00, 1, 4),
    ('Forage 40m', 12000000.00, 1, 4),
    ('Équipement complet', 5000000.00, 1, 4),
    ('Formation communautaire', 1500000.00, 1, 4),
    ('Main d''œuvre', 1000000.00, 1, 4);

-- ============================================
-- FIN DU SCRIPT DATA
-- ============================================