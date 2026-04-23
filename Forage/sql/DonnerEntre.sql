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

-- -- ============================================
-- -- DEMANDES
-- -- ============================================
-- INSERT INTO demandes (date_demande, lieu, district, client_id) VALUES 
--     ('2024-04-12', 'Antsirabe', 'test', 1),
--     ('2024-04-14', 'Antsirabe II', 'test 2', 4),
--     ('2024-04-10', 'Antananarivo', 'Analamanga', 5);


-- ============================================
-- FIN DU SCRIPT DATA
-- ============================================