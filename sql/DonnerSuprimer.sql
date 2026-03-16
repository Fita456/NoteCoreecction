use exam_correction;

-- Désactiver les vérifications de clés étrangères temporairement
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE etudiant;
TRUNCATE TABLE professeur;
TRUNCATE TABLE matiere;
TRUNCATE TABLE notes;
TRUNCATE TABLE resolution;
TRUNCATE TABLE comparateur;
TRUNCATE TABLE parametre;
TRUNCATE TABLE note_final;

-- Réactiver les vérifications de clés étrangères
SET FOREIGN_KEY_CHECKS = 1;

-- Vérification
SELECT '=== TOUTES LES DONNÉES ONT ÉTÉ SUPPRIMÉES ===' AS Info;
SELECT '' AS '';
SELECT 'État des tables :' AS Info;
SELECT 'Eleve' AS TableName, COUNT(*) AS Lignes FROM etudiant
UNION ALL
SELECT 'Professeur', COUNT(*) FROM professeur
UNION ALL
SELECT 'Matiere', COUNT(*) FROM matiere
UNION ALL
SELECT 'Notes', COUNT(*) FROM notes
UNION ALL
SELECT 'Resolution', COUNT(*) FROM resolution
UNION ALL
SELECT 'Comparateur', COUNT(*) FROM comparateur
UNION ALL
SELECT 'Parametre', COUNT(*) FROM parametre
UNION ALL
SELECT 'note_final', COUNT(*) FROM note_final;