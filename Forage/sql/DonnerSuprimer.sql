use forage;

-- Désactiver les vérifications de clés étrangères temporairement
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE demande_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE details_devis RESTART IDENTITY CASCADE;
TRUNCATE TABLE devis RESTART IDENTITY CASCADE;
TRUNCATE TABLE demandes RESTART IDENTITY CASCADE;
TRUNCATE TABLE clients RESTART IDENTITY CASCADE;
TRUNCATE TABLE status RESTART IDENTITY CASCADE;
TRUNCATE TABLE types_devis RESTART IDENTITY CASCADE;

SET FOREIGN_KEY_CHECKS = 1;

-- Vérification
SELECT '=== TOUTES LES DONNÉES ONT ÉTÉ SUPPRIMÉES ===' AS Info;
SELECT '' AS '';
SELECT 'État des tables :' AS Info;
SELECT 'demande_status' AS TableName, COUNT(*) AS Lignes FROM demande_status
UNION ALL
SELECT 'details_devis', COUNT(*) FROM details_devis
UNION ALL
SELECT 'devis', COUNT(*) FROM devis
UNION ALL
SELECT 'demandes', COUNT(*) FROM demandes
UNION ALL
SELECT 'clients', COUNT(*) FROM clients
UNION ALL
SELECT 'status', COUNT(*) FROM status
UNION ALL
SELECT 'types_devis', COUNT(*) FROM types_devis;
