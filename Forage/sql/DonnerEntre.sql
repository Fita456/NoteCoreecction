-- ============================================
-- FICHIER: data_test.sql
-- DESCRIPTION: Données de test pour Forage
-- BASE: MySQL / MariaDB
-- ============================================

USE forage;

-- ============================================
-- VIDER LES TABLES (dans l'ordre des dépendances)
-- ============================================
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE demande_status;
TRUNCATE TABLE details_devis;
TRUNCATE TABLE devis;
TRUNCATE TABLE demandes;
TRUNCATE TABLE clients;
TRUNCATE TABLE status;
TRUNCATE TABLE types_devis;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- TABLE: status
-- ============================================
INSERT INTO status (id, libelle) VALUES 
    (1, 'En attente'),
    (2, 'En cours d''étude'),
    (3, 'Devis envoyé'),
    (4, 'Validé'),
    (5, 'En cours de réalisation'),
    (6, 'Terminé'),
    (7, 'Rejeté'),
    (8, 'Annulé');

-- ============================================
-- TABLE: types_devis
-- ============================================
INSERT INTO types_devis (id, libelle) VALUES 
    (1, 'Forage simple (0-30m)'),
    (2, 'Forage moyen (30-60m)'),
    (3, 'Forage profond (60m+)'),
    (4, 'Réhabilitation'),
    (5, 'Entretien'),
    (6, 'Étude de faisabilité');

-- ============================================
-- TABLE: clients
-- ============================================
INSERT INTO clients (id, nom, contact) VALUES 
    (1, 'Commune Rurale Ambohimanga', '034 12 345 67'),
    (2, 'ONG Eau Pour Tous', 'contact@eaupourtous.mg'),
    (3, 'Société JIRAMA', '020 22 123 45'),
    (4, 'Association Villageoise Antsirabe', '033 98 765 43'),
    (5, 'Ministère de l''Eau', 'info@mineau.gov.mg'),
    (6, 'Commune Urbaine Fianarantsoa', '034 55 666 77'),
    (7, 'ONG Aide Humanitaire', 'aide@humanitaire.org'),
    (8, 'Hôpital Régional Toamasina', '020 53 321 00'),
    (9, 'École Primaire Publique Ankazobe', '033 11 222 33'),
    (10, 'Coopérative Agricole Betafo', '034 44 555 66');

-- ============================================
-- TABLE: demandes
-- ============================================
INSERT INTO demandes (id, date_demande, lieu, district, client_id) VALUES 
    -- Demandes terminées
    (1, '2024-01-10', 'Fokontany Ambohitraivo', 'Antananarivo Atsimondrano', 1),
    (2, '2024-01-15', 'Village Mahazoarivo', 'Antsirabe II', 4),
    
    -- Demandes en cours
    (3, '2024-02-01', 'Quartier Isotry', 'Antananarivo Renivohitra', 3),
    (4, '2024-02-10', 'Commune Ambatolampy', 'Ambatolampy', 2),
    (5, '2024-02-15', 'Zone Rurale Betafo', 'Betafo', 10),
    
    -- Demandes récentes
    (6, '2024-02-20', 'Fokontany Ambalavao', 'Fianarantsoa I', 6),
    (7, '2024-02-25', 'Village Moramanga', 'Moramanga', 7),
    (8, '2024-03-01', 'Hôpital Central', 'Toamasina I', 8),
    (9, '2024-03-05', 'École EPP Ankazobe', 'Ankazobe', 9),
    (10, '2024-03-10', 'Siège Ministère', 'Antananarivo Renivohitra', 5),
    
    -- Demandes rejetées/annulées
    (11, '2024-01-20', 'Zone inondable', 'Betsiboka', 7),
    (12, '2024-02-05', 'Terrain privé contesté', 'Vakinankaratra', 4);

-- ============================================
-- TABLE: demande_status (Historique des statuts)
-- ============================================

-- Demande 1 : TERMINÉE (parcours complet)
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (1, '2024-01-10 08:00:00', 1, 1, 'Demande reçue par courrier'),
    (2, '2024-01-12 09:30:00', 1, 2, 'Étude géophysique planifiée'),
    (3, '2024-01-18 14:00:00', 1, 3, 'Devis envoyé au client'),
    (4, '2024-01-22 10:00:00', 1, 4, 'Devis accepté par le client'),
    (5, '2024-01-25 07:00:00', 1, 5, 'Début des travaux de forage'),
    (6, '2024-02-15 16:00:00', 1, 6, 'Forage terminé - 25m - Eau trouvée');

-- Demande 2 : TERMINÉE (parcours complet)
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (7, '2024-01-15 09:00:00', 2, 1, 'Demande reçue'),
    (8, '2024-01-17 11:00:00', 2, 2, 'Visite terrain effectuée'),
    (9, '2024-01-20 15:30:00', 2, 3, 'Devis transmis'),
    (10, '2024-01-25 09:00:00', 2, 4, 'Validation du budget communal'),
    (11, '2024-01-28 06:30:00', 2, 5, 'Équipe sur place'),
    (12, '2024-02-20 17:00:00', 2, 6, 'Travaux achevés avec succès');

-- Demande 3 : EN COURS DE RÉALISATION
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (13, '2024-02-01 10:00:00', 3, 1, 'Demande urgente reçue'),
    (14, '2024-02-03 14:00:00', 3, 2, 'Étude prioritaire lancée'),
    (15, '2024-02-08 11:00:00', 3, 3, 'Devis express envoyé'),
    (16, '2024-02-10 16:00:00', 3, 4, 'Accord de la JIRAMA'),
    (17, '2024-02-12 07:00:00', 3, 5, 'Travaux en cours - profondeur actuelle 40m');

-- Demande 4 : DEVIS ENVOYÉ (en attente validation)
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (18, '2024-02-10 08:30:00', 4, 1, 'Demande reçue de l''ONG'),
    (19, '2024-02-14 10:00:00', 4, 2, 'Étude de faisabilité en cours'),
    (20, '2024-02-20 15:00:00', 4, 3, 'Devis détaillé envoyé - en attente retour');

-- Demande 5 : EN COURS D'ÉTUDE
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (21, '2024-02-15 09:00:00', 5, 1, 'Demande reçue'),
    (22, '2024-02-18 11:30:00', 5, 2, 'Analyse des données géologiques');

-- Demande 6 : EN ATTENTE (récente)
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (23, '2024-02-20 14:00:00', 6, 1, 'Nouvelle demande enregistrée');

-- Demande 7 : EN ATTENTE (récente)
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (24, '2024-02-25 10:00:00', 7, 1, 'Demande humanitaire prioritaire');

-- Demande 8 : EN COURS D'ÉTUDE
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (25, '2024-03-01 08:00:00', 8, 1, 'Demande urgente hôpital'),
    (26, '2024-03-02 09:00:00', 8, 2, 'Étude accélérée lancée');

-- Demande 9 : EN ATTENTE
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (27, '2024-03-05 11:00:00', 9, 1, 'Demande pour école primaire');

-- Demande 10 : VALIDÉ (en attente début travaux)
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (28, '2024-03-10 09:00:00', 10, 1, 'Demande ministérielle'),
    (29, '2024-03-11 14:00:00', 10, 2, 'Étude express'),
    (30, '2024-03-12 10:00:00', 10, 3, 'Devis validé en interne'),
    (31, '2024-03-13 16:00:00', 10, 4, 'Budget alloué - travaux à planifier');

-- Demande 11 : REJETÉE
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (32, '2024-01-20 10:00:00', 11, 1, 'Demande reçue'),
    (33, '2024-01-25 14:00:00', 11, 2, 'Étude terrain effectuée'),
    (34, '2024-01-28 11:00:00', 11, 7, 'REJETÉ - Zone inondable non exploitable');

-- Demande 12 : ANNULÉE
INSERT INTO demande_status (id, date_status, demande_id, status_id, commentaire) VALUES 
    (35, '2024-02-05 09:00:00', 12, 1, 'Demande reçue'),
    (36, '2024-02-10 15:00:00', 12, 2, 'Étude préliminaire'),
    (37, '2024-02-15 10:00:00', 12, 8, 'ANNULÉ - Litige foncier en cours');

-- ============================================
-- TABLE: devis (pour demandes avec devis)
-- ============================================
INSERT INTO devis (id, date_devis, montant_total, type_devis_id, demande_id) VALUES 
    (1, '2024-01-18', 15500000.00, 1, 1),  -- Demande 1 terminée
    (2, '2024-01-20', 28000000.00, 2, 2),  -- Demande 2 terminée
    (3, '2024-02-08', 45000000.00, 3, 3),  -- Demande 3 en cours
    (4, '2024-02-20', 22000000.00, 2, 4),  -- Demande 4 devis envoyé
    (5, '2024-03-12', 12000000.00, 1, 10); -- Demande 10 validée

-- ============================================
-- TABLE: details_devis
-- ============================================

-- Devis 1 (Demande 1 - Forage simple)
INSERT INTO details_devis (id, libelle, montant, devis_id) VALUES 
    (1, 'Étude géophysique', 2000000.00, 1),
    (2, 'Forage 25m', 8000000.00, 1),
    (3, 'Tubage PVC', 1500000.00, 1),
    (4, 'Pompe manuelle India Mark II', 2500000.00, 1),
    (5, 'Main d''œuvre', 1500000.00, 1);

-- Devis 2 (Demande 2 - Forage moyen)
INSERT INTO details_devis (id, libelle, montant, devis_id) VALUES 
    (6, 'Étude géophysique approfondie', 3000000.00, 2),
    (7, 'Forage 45m', 15000000.00, 2),
    (8, 'Tubage acier inox', 3000000.00, 2),
    (9, 'Pompe immergée', 4000000.00, 2),
    (10, 'Installation électrique', 2000000.00, 2),
    (11, 'Main d''œuvre', 1000000.00, 2);

-- Devis 3 (Demande 3 - Forage profond JIRAMA)
INSERT INTO details_devis (id, libelle, montant, devis_id) VALUES 
    (12, 'Étude hydrogéologique complète', 5000000.00, 3),
    (13, 'Forage 80m', 25000000.00, 3),
    (14, 'Tubage acier renforcé', 5000000.00, 3),
    (15, 'Pompe industrielle', 6000000.00, 3),
    (16, 'Système de filtration', 2000000.00, 3),
    (17, 'Main d''œuvre spécialisée', 2000000.00, 3);

-- Devis 4 (Demande 4 - ONG)
INSERT INTO details_devis (id, libelle, montant, devis_id) VALUES 
    (18, 'Étude de site', 2500000.00, 4),
    (19, 'Forage 40m', 12000000.00, 4),
    (20, 'Équipement complet', 5000000.00, 4),
    (21, 'Formation communautaire', 1500000.00, 4),
    (22, 'Main d''œuvre', 1000000.00, 4);

-- Devis 5 (Demande 10 - Ministère)
INSERT INTO details_devis (id, libelle, montant, devis_id) VALUES 
    (23, 'Étude rapide', 1500000.00, 5),
    (24, 'Forage 20m', 6000000.00, 5),
    (25, 'Pompe manuelle', 2500000.00, 5),
    (26, 'Aménagement surface', 1000000.00, 5),
    (27, 'Main d''œuvre', 1000000.00, 5);

-- ============================================
-- FIN DU SCRIPT
-- ============================================

SELECT 'Données insérées avec succès!' AS Message;