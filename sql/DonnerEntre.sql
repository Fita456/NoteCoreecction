use exam_correction;

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
('Correcteur2', 2),  -- PHP
('Correcteur3', 1);  -- JAVA (2ème correcteur pour JAVA)

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

-- Insertion des Paramètres (CORRIGÉ)
-- Matière JAVA (id=1) a 2 règles
INSERT INTO parametre (matiere_id, comparateur_id, resolution_id, seuil) VALUES 
(1, 1, 1,7),  -- JAVA: MAX < 7 (l'écart maximum entre notes doit être < 7), donc on prend le note maximum ou max si le seuil ou entre les notes sont inferieur a 7
(1, 4, 3, 7);  -- JAVA: AVERAGE >= 7 (la moyenne des écarts doit être >= 7), donc en prend la moyenne ou average des notes s'i est superieur ou egale a 7 

-- Matière PHP (id=2) a 2 règles (CORRIGÉ - comparateur_id 5 n'existe pas)
INSERT INTO parametre (matiere_id, comparateur_id, resolution_id, seuil) VALUES 
(2, 3, 1, 2),  -- PHP: MIN <= 2 (la note minimale doit être <= 2), donc en prend le note minimal si la note du seuil est superieur ou egale a 2
(2, 2, 2, 2);  -- PHP: MAX > 2 (l'écart maximum doit être > 2), donc en prend le note maximale si la note du seuil est inferieur a 2

-- Insertion des Notes
INSERT INTO notes (etudiant_id, professeur_id, matiere_id, valeur) VALUES 
-- Candidat1
(1, 1, 1, 15),  -- Candidat1, Correcteur1, JAVA, Note: 12
(1, 2, 1, 10),  -- Candidat1, Correcteur2, PHP, Note: 11
(1, 3, 1, 12),   -- Candidat1, Correcteur1, JAVA, Note: 7 (2ème correction)
(2, 1, 1, 9),  -- Candidat1, Correcteur2, PHP, Note: 11 (2ème correction)
(2, 2, 1, 8),  -- Candidat1, Correcteur3, JAVA, Note: 14 (2ème correcteur pour JAVA)
(2, 3, 1, 11),  -- Candidat1, Correcteur3, JAVA, Note: 14 (2ème correcteur pour JAVA)

(1, 1, 2, 10),  -- Candidat2, Correcteur1, JAVA, Note: 13
(2, 1, 2, 13),  -- Candidat2, Correcteur2, PHP, Note: 10
(1, 2, 2, 10),  -- Candidat2, Correcteur1, JAVA, Note: 14 (2ème correction)
(2, 2, 2, 11);  -- Candidat2, Correcteur2, PHP, Note: 16 (2ème correction)