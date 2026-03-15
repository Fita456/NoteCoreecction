use exam_correction;

-- ================================================
-- INSERTION DE VOS DONNÉES
-- ================================================

-- Insertion des Étudiants (Candidats)
INSERT INTO etudiant (nom, date_naissance, etablissement_origine) VALUES 
('Candidat1', '2000-05-15', 'Lycée Jean Moulin'),
('Candidat2', '1999-08-22', 'Lycée Victor Hugo');

-- Insertion des Matières
INSERT INTO matiere (name) VALUES
('JAVA'),
('PHP');

-- Insertion des Professeurs (Correcteurs)
INSERT INTO professeur (name, id_matiere) VALUES
('Correcteur1', 1),  -- JAVA
('Correcteur2', 2), -- JAVA
('correcteur3', 1);

-- Insertion des Résolutions
INSERT INTO resolution (ref) VALUES
('MIN'),
('MAX'),
('AVERAGE');

-- Insertion des Comparateurs
INSERT INTO comparateur (ref) VALUES
('INFERIEUR'),           -- 1: <
('SUPERIEUR'),           -- 2: >
('INFERIEUR_OU_EGAL'),   -- 3: <=
('SUPERIEUR_OU_EGAL');   -- 4: >=

-- Insertion des Paramètres 
INSERT INTO parametre (matiere_id, comparateur_id, resolution_id, seuil) VALUES 
(1, 1, 2, 3),
(1, 4, 3, 3);

-- Matière PHP (id=2) a 2 règles (CORRIGÉ - comparateur_id 5 n'existe pas)
INSERT INTO parametre (matiere_id, comparateur_id, resolution_id, seuil) VALUES 
(2, 3, 1, 4),
(2, 2, 2, 4);

-- Insertion des Notes
INSERT INTO notes (etudiant_id, professeur_id, matiere_id, valeur) VALUES 
-- Candidat1
(1, 1, 1, 13),
(1, 2, 1, 12),
(1, 3, 1, 8),
(1, 1, 2, 12),
(1, 2, 2, 10),

-- Candidat2
(2, 1, 1, 12),
(2, 2, 1, 10),
(2, 3, 1, 16),
(2, 1, 2, 15),
(2, 2, 2, 13);