-- ================================================
-- CRÉATION DE LA BASE DE DONNÉES
-- ================================================

DROP DATABASE IF EXISTS exam_correction;
CREATE DATABASE exam_correction CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE exam_correction;

-- ================================================
-- CRÉATION DES TABLES
-- ================================================

-- Table Etudiant
CREATE TABLE etudiant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    date_naissance DATE,
    etablissement_origine VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table Matiere
CREATE TABLE matiere (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table Professeur
CREATE TABLE professeur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_matiere BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table Resolution
CREATE TABLE resolution (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ref VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_resolution CHECK (ref IN ('MIN', 'MAX', 'AVERAGE'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table Comparateur
CREATE TABLE comparateur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ref VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table Notes
CREATE TABLE notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    etudiant_id BIGINT NOT NULL,
    professeur_id BIGINT NOT NULL,
    matiere_id BIGINT NOT NULL,
    valeur DOUBLE NOT NULL,
    date_heure DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (etudiant_id) REFERENCES etudiant(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (professeur_id) REFERENCES professeur(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (matiere_id) REFERENCES matiere(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_etudiant_matiere (etudiant_id, matiere_id),
    INDEX idx_professeur (professeur_id),
    INDEX idx_date (date_heure)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table Parametre
CREATE TABLE parametre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matiere_id BIGINT NOT NULL,
    comparateur_id BIGINT NOT NULL,
    resolution_id BIGINT NOT NULL,
    seuil DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (matiere_id) REFERENCES matiere(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (comparateur_id) REFERENCES comparateur(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (resolution_id) REFERENCES resolution(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_matiere (matiere_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table NoteFinal
CREATE TABLE note_final (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matiere_id BIGINT NOT NULL,
    etudiant_id BIGINT NOT NULL,
    parametre_id BIGINT NOT NULL,
    note_final DOUBLE NOT NULL,
    date_entre DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (matiere_id) REFERENCES matiere(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (etudiant_id) REFERENCES etudiant(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parametre_id) REFERENCES parametre(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE KEY unique_etudiant_matiere (etudiant_id, matiere_id, parametre_id),
    INDEX idx_etudiant (etudiant_id),
    INDEX idx_matiere (matiere_id),
    INDEX idx_date (date_entre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;