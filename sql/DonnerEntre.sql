USE exam_correction;

-- ================================================
-- INSERTION DE DONNÉES DE TEST
-- ================================================

-- Insertion des Résolutions (MIN, MAX, AVERAGE)
INSERT INTO resolution (id, ref) VALUES
(1, 'MIN'),
(2, 'MAX'),
(3, 'AVERAGE');

-- Insertion des Comparateurs (INFERIEUR, SUPERIEUR)
INSERT INTO comparateur (id, ref) VALUES
(1, 'INFERIEUR'),
(2, 'SUPERIEUR');

-- Insertion des Matières
INSERT INTO matiere (id, name) VALUES
(1, 'Mathématiques'),
(2, 'Physique'),
(3, 'Informatique'),
(4, 'Chimie'),
(5, 'Biologie'),
(6, 'Français'),
(7, 'Anglais'),
(8, 'Histoire'),
(9, 'Géographie'),
(10, 'Philosophie');

-- Insertion des Étudiants
INSERT INTO etudiant (id, nom, date_naissance, etablissement_origine) VALUES
(1, 'DUPONT Jean', '2000-05-15', 'Lycée Victor Hugo'),
(2, 'MARTIN Sophie', '2001-03-22', 'Lycée Pasteur'),
(3, 'BERNARD Lucas', '2000-11-08', 'Lycée Descartes'),
(4, 'THOMAS Marie', '2001-07-30', 'Lycée Voltaire'),
(5, 'PETIT Paul', '2000-09-12', 'Lycée Rousseau'),
(6, 'ROBERT Emma', '2001-01-25', 'Lycée Molière'),
(7, 'RICHARD Hugo', '2000-06-18', 'Lycée Racine'),
(8, 'DUBOIS Léa', '2001-04-05', 'Lycée Balzac'),
(9, 'MOREAU Antoine', '2000-12-20', 'Lycée Zola'),
(10, 'LAURENT Chloé', '2001-08-14', 'Lycée Hugo'),
(11, 'SIMON Alexandre', '2000-02-28', 'Lycée Lamartine'),
(12, 'MICHEL Julie', '2001-10-03', 'Lycée Rimbaud'),
(13, 'LEFEBVRE Thomas', '2000-07-09', 'Lycée Baudelaire'),
(14, 'LEROY Camille', '2001-05-17', 'Lycée Verlaine'),
(15, 'ROUX Nicolas', '2000-03-31', 'Lycée Apollinaire');

-- Insertion des Professeurs (plusieurs professeurs par matière)
INSERT INTO professeur (id, name, id_matiere) VALUES
-- Mathématiques
(1, 'Prof. LAMBERT Pierre', 1),
(2, 'Prof. GIRARD Claire', 1),
(3, 'Prof. BONNET Michel', 1),
-- Physique
(4, 'Prof. FOURNIER Anne', 2),
(5, 'Prof. MERCIER Paul', 2),
-- Informatique
(6, 'Prof. BLANC Sophie', 3),
(7, 'Prof. GUERIN Marc', 3),
(8, 'Prof. BOYER Julie', 3),
-- Chimie
(9, 'Prof. GARNIER Luc', 4),
(10, 'Prof. CHEVALIER Marie', 4),
-- Biologie
(11, 'Prof. FRANCOIS Jean', 5),
(12, 'Prof. FONTAINE Lisa', 5),
-- Français
(13, 'Prof. ROUSSEAU Pierre', 6),
(14, 'Prof. VINCENT Emma', 6),
-- Anglais
(15, 'Prof. MULLER David', 7),
-- Histoire
(16, 'Prof. HENRY Catherine', 8),
-- Géographie
(17, 'Prof. GAUTIER Robert', 9),
-- Philosophie
(18, 'Prof. MOREL Isabelle', 10);

-- Insertion des Paramètres pour chaque matière
INSERT INTO parametre (id, matiere_id, comparateur_id, resolution_id, seuil) VALUES
-- Mathématiques: si différence MIN < 5 points
(1, 1, 1, 1, 5.0),
-- Physique: si différence MAX > 3 points
(2, 2, 2, 2, 3.0),
-- Informatique: si différence AVERAGE > 2.5 points
(3, 3, 2, 3, 2.5),
-- Chimie: si différence MIN < 4 points
(4, 4, 1, 1, 4.0),
-- Biologie: si différence MAX > 3.5 points
(5, 5, 2, 2, 3.5),
-- Français: si différence AVERAGE > 2 points
(6, 6, 2, 3, 2.0),
-- Anglais: si différence MIN < 3 points
(7, 7, 1, 1, 3.0),
-- Histoire: si différence MAX > 4 points
(8, 8, 2, 2, 4.0),
-- Géographie: si différence AVERAGE > 2 points
(9, 9, 2, 3, 2.0),
-- Philosophie: si différence MIN < 5 points
(10, 10, 1, 1, 5.0);

-- Insertion des Notes (plusieurs professeurs notent le même étudiant pour la même matière)
INSERT INTO notes (etudiant_id, professeur_id, matiere_id, valeur, date_heure) VALUES
-- Étudiant 1 (DUPONT Jean) - Mathématiques (3 professeurs)
(1, 1, 1, 14.5, '2024-01-15 10:00:00'),
(1, 2, 1, 15.0, '2024-01-15 11:00:00'),
(1, 3, 1, 14.0, '2024-01-15 12:00:00'),
-- Étudiant 1 - Physique (2 professeurs)
(1, 4, 2, 13.5, '2024-01-16 10:00:00'),
(1, 5, 2, 14.0, '2024-01-16 11:00:00'),
-- Étudiant 1 - Informatique (3 professeurs)
(1, 6, 3, 16.0, '2024-01-17 10:00:00'),
(1, 7, 3, 15.5, '2024-01-17 11:00:00'),
(1, 8, 3, 16.5, '2024-01-17 12:00:00'),

-- Étudiant 2 (MARTIN Sophie) - Mathématiques (3 professeurs)
(2, 1, 1, 16.0, '2024-01-15 10:00:00'),
(2, 2, 1, 15.5, '2024-01-15 11:00:00'),
(2, 3, 1, 16.5, '2024-01-15 12:00:00'),
-- Étudiant 2 - Chimie (2 professeurs)
(2, 9, 4, 14.0, '2024-01-18 10:00:00'),
(2, 10, 4, 13.5, '2024-01-18 11:00:00'),

-- Étudiant 3 (BERNARD Lucas) - Informatique (3 professeurs)
(3, 6, 3, 17.0, '2024-01-17 10:00:00'),
(3, 7, 3, 16.0, '2024-01-17 11:00:00'),
(3, 8, 3, 17.5, '2024-01-17 12:00:00'),
-- Étudiant 3 - Physique (2 professeurs)
(3, 4, 2, 15.0, '2024-01-16 10:00:00'),
(3, 5, 2, 14.5, '2024-01-16 11:00:00'),

-- Étudiant 4 (THOMAS Marie) - Français (2 professeurs)
(4, 13, 6, 15.5, '2024-01-19 10:00:00'),
(4, 14, 6, 16.0, '2024-01-19 11:00:00'),
-- Étudiant 4 - Mathématiques (3 professeurs)
(4, 1, 1, 13.0, '2024-01-15 10:00:00'),
(4, 2, 1, 12.5, '2024-01-15 11:00:00'),
(4, 3, 1, 13.5, '2024-01-15 12:00:00'),

-- Étudiant 5 (PETIT Paul) - Biologie (2 professeurs)
(5, 11, 5, 14.5, '2024-01-20 10:00:00'),
(5, 12, 5, 15.0, '2024-01-20 11:00:00'),
-- Étudiant 5 - Chimie (2 professeurs)
(5, 9, 4, 13.0, '2024-01-18 10:00:00'),
(5, 10, 4, 12.5, '2024-01-18 11:00:00'),

-- Étudiant 6 (ROBERT Emma) - Anglais (1 professeur)
(6, 15, 7, 16.5, '2024-01-21 10:00:00'),
-- Étudiant 6 - Français (2 professeurs)
(6, 13, 6, 17.0, '2024-01-19 10:00:00'),
(6, 14, 6, 16.5, '2024-01-19 11:00:00'),

-- Étudiant 7 (RICHARD Hugo) - Histoire (1 professeur)
(7, 16, 8, 14.0, '2024-01-22 10:00:00'),
-- Étudiant 7 - Philosophie (1 professeur)
(7, 18, 10, 13.5, '2024-01-23 10:00:00'),

-- Étudiant 8 (DUBOIS Léa) - Géographie (1 professeur)
(8, 17, 9, 15.0, '2024-01-24 10:00:00'),
-- Étudiant 8 - Mathématiques (3 professeurs)
(8, 1, 1, 12.0, '2024-01-15 10:00:00'),
(8, 2, 1, 11.5, '2024-01-15 11:00:00'),
(8, 3, 1, 12.5, '2024-01-15 12:00:00'),

-- Étudiant 9 (MOREAU Antoine) - Informatique (3 professeurs)
(9, 6, 3, 18.0, '2024-01-17 10:00:00'),
(9, 7, 3, 17.5, '2024-01-17 11:00:00'),
(9, 8, 3, 18.5, '2024-01-17 12:00:00'),

-- Étudiant 10 (LAURENT Chloé) - Physique (2 professeurs)
(10, 4, 2, 16.5, '2024-01-16 10:00:00'),
(10, 5, 2, 16.0, '2024-01-16 11:00:00'),

-- Étudiant 11 (SIMON Alexandre) - Chimie (2 professeurs)
(11, 9, 4, 15.5, '2024-01-18 10:00:00'),
(11, 10, 4, 15.0, '2024-01-18 11:00:00'),

-- Étudiant 12 (MICHEL Julie) - Biologie (2 professeurs)
(12, 11, 5, 16.0, '2024-01-20 10:00:00'),
(12, 12, 5, 15.5, '2024-01-20 11:00:00'),

-- Étudiant 13 (LEFEBVRE Thomas) - Français (2 professeurs)
(13, 13, 6, 14.0, '2024-01-19 10:00:00'),
(13, 14, 6, 14.5, '2024-01-19 11:00:00'),

-- Étudiant 14 (LEROY Camille) - Anglais (1 professeur)
(14, 15, 7, 17.5, '2024-01-21 10:00:00'),

-- Étudiant 15 (ROUX Nicolas) - Histoire (1 professeur)
(15, 16, 8, 13.0, '2024-01-22 10:00:00');

-- Insertion des Notes Finales (calculées selon les paramètres)
INSERT INTO note_final (matiere_id, etudiant_id, parametre_id, note_final, date_entre) VALUES
-- Mathématiques
(1, 1, 1, 14.5, '2024-01-25 10:00:00'),  -- Moyenne des 3 notes
(1, 2, 1, 16.0, '2024-01-25 10:00:00'),
(1, 4, 1, 13.0, '2024-01-25 10:00:00'),
(1, 8, 1, 12.0, '2024-01-25 10:00:00'),

-- Physique
(2, 1, 2, 13.75, '2024-01-25 11:00:00'),
(2, 3, 2, 14.75, '2024-01-25 11:00:00'),
(2, 10, 2, 16.25, '2024-01-25 11:00:00'),

-- Informatique
(3, 1, 3, 16.0, '2024-01-25 12:00:00'),
(3, 3, 3, 16.83, '2024-01-25 12:00:00'),
(3, 9, 3, 18.0, '2024-01-25 12:00:00'),

-- Chimie
(4, 2, 4, 13.75, '2024-01-25 13:00:00'),
(4, 5, 4, 12.75, '2024-01-25 13:00:00'),
(4, 11, 4, 15.25, '2024-01-25 13:00:00'),

-- Biologie
(5, 5, 5, 14.75, '2024-01-25 14:00:00'),
(5, 12, 5, 15.75, '2024-01-25 14:00:00'),

-- Français
(6, 4, 6, 15.75, '2024-01-25 15:00:00'),
(6, 6, 6, 16.75, '2024-01-25 15:00:00'),
(6, 13, 6, 14.25, '2024-01-25 15:00:00'),

-- Anglais
(7, 6, 7, 16.5, '2024-01-25 16:00:00'),
(7, 14, 7, 17.5, '2024-01-25 16:00:00'),

-- Histoire
(8, 7, 8, 14.0, '2024-01-25 17:00:00'),
(8, 15, 8, 13.0, '2024-01-25 17:00:00'),

-- Géographie
(9, 8, 9, 15.0, '2024-01-25 18:00:00'),

-- Philosophie
(10, 7, 10, 13.5, '2024-01-25 19:00:00');