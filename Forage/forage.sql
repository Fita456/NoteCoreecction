CREATE DATABASE IF NOT EXISTS forage;
USE forage;

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL
);

CREATE TABLE status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE types_devis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE demandes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_demande DATE NOT NULL,
    lieu VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    client_id INT NOT NULL,

    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE devis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_devis DATE NOT NULL,
    montant_total DECIMAL(15, 2) DEFAULT 0.00,
    type_devis_id INT NOT NULL,
    demande_id INT,

    FOREIGN KEY (type_devis_id) REFERENCES types_devis(id),
    FOREIGN KEY (demande_id) REFERENCES demandes(id)
);

CREATE TABLE details_devis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    prix_unitaire DECIMAL(15, 2) NOT NULL,
    quantite INT NOT NULL,
    Total DECIMAL(15, 2) AS (prix_unitaire * quantite) STORED,
    devis_id INT NOT NULL,

    FOREIGN KEY (devis_id) REFERENCES devis(id)
);

CREATE TABLE demande_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_status TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    commentaire TEXT,
    demande_id INT NOT NULL,
    status_id INT NOT NULL,

    FOREIGN KEY (demande_id) REFERENCES demandes(id),
    FOREIGN KEY (status_id) REFERENCES status(id)
);