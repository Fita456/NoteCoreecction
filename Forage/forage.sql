CREATE DATABASE forage;
use forage;

CREATE TABLE clients (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL
);

CREATE TABLE status (
    id BIGSERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE types_devis (
    id BIGSERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE demandes (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    lieu VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    client_id BIGINT NOT NULL
);

CREATE TABLE devis (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    montant_total DECIMAL(15, 2) DEFAULT 0.00,
    type_devis_id BIGINT NOT NULL,
    demande_id BIGINT
);

CREATE TABLE details_devis (
    id BIGSERIAL PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    montant DECIMAL(15, 2) NOT NULL,
    devis_id BIGINT NOT NULL
);

CREATE TABLE demande_status (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    commentaire TEXT,
    demande_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL
);
