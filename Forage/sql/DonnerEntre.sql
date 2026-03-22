use forage;

-- ============================================
-- INSERTION: status
-- ============================================
INSERT INTO status (libelle) VALUES 
    ('En attente'),
    ('En cours'),
    ('Validé'),
    ('Rejeté'),
    ('Terminé'),
    ('Annulé');

-- ============================================
-- INSERTION: types_devis
-- ============================================
INSERT INTO types_devis (libelle) VALUES 
    ('Forage simple'),
    ('Forage profond'),
    ('Réhabilitation'),
    ('Entretien'),
    ('Étude de faisabilité');

-- ============================================
-- INSERTION: clients (données exemple)
-- ============================================
INSERT INTO clients (nom, contact) VALUES 
    ('Commune Rurale Ambohimanga', '034 12 345 67'),
    ('ONG Eau Pour Tous', 'contact@eaupourtous.mg'),
    ('Société JIRAMA', '020 22 123 45'),
    ('Association Villageoise Antsirabe', '033 98 765 43'),
    ('Ministère de l''Eau', 'info@mineau.gov.mg');

-- ============================================
-- INSERTION: demandes (données exemple)
-- ============================================
INSERT INTO demandes (date, lieu, district, client_id) VALUES 
    ('2024-01-15', 'Fokontany Ambohitraivo', 'Antananarivo Atsimondrano', 1),
    ('2024-01-20', 'Village Mahazoarivo', 'Antsirabe II', 4),
    ('2024-02-01', 'Quartier Isotry', 'Antananarivo Renivohitra', 3),
    ('2024-02-10', 'Commune Ambatolampy', 'Ambatolampy', 2),
    ('2024-02-15', 'Zone Rurale Betafo', 'Betafo', 5);

-- ============================================
-- INSERTION: demande_status (statuts initiaux)
-- ============================================
INSERT INTO demande_status (date, demande_id, status_id, commentaire) VALUES 
    ('2024-01-15 08:00:00', 1, 1, 'Demande reçue'),
    ('2024-01-18 10:30:00', 1, 2, 'Étude en cours'),
    ('2024-01-20 09:00:00', 2, 1, 'Demande reçue'),
    ('2024-02-01 14:00:00', 3, 1, 'Demande reçue'),
    ('2024-02-05 11:00:00', 3, 3, 'Demande validée par le directeur'),
    ('2024-02-10 08:30:00', 4, 1, 'Demande reçue'),
    ('2024-02-15 09:15:00', 5, 1, 'Demande reçue'),
    ('2024-02-20 16:00:00', 5, 4, 'Budget insuffisant');

-- ============================================
-- INSERTION: devis (données exemple)
-- ============================================
INSERT INTO devis (date, montant_total, type_devis_id, demande_id) VALUES 
    ('2024-01-25', 15000000.00, 1, 1),
    ('2024-02-08', 45000000.00, 2, 3),
    ('2024-02-12', 8500000.00, 3, 4);

-- ============================================
-- INSERTION: details_devis (lignes de devis)
-- ============================================
-- Devis 1
INSERT INTO details_devis (libelle, montant, devis_id) VALUES 
    ('Étude géophysique', 2000000.00, 1),
    ('Forage 30m', 9000000.00, 1),
    ('Pompe manuelle', 2500000.00, 1),
    ('Main d''œuvre', 1500000.00, 1);

-- Devis 2
INSERT INTO details_devis (libelle, montant, devis_id) VALUES 
    ('Étude géophysique approfondie', 5000000.00, 2),
    ('Forage 80m', 25000000.00, 2),
    ('Pompe électrique', 8000000.00, 2),
    ('Installation électrique', 4000000.00, 2),
    ('Main d''œuvre', 3000000.00, 2);

-- Devis 3
INSERT INTO details_devis (libelle, montant, devis_id) VALUES 
    ('Diagnostic', 1000000.00, 3),
    ('Nettoyage forage', 2500000.00, 3),
    ('Remplacement pompe', 4000000.00, 3),
    ('Main d''œuvre', 1000000.00, 3);