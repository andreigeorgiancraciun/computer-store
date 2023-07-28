-- Table for "produse"
CREATE TABLE IF NOT EXISTS produse (
                                       id SERIAL PRIMARY KEY,
                                       nume VARCHAR(100) NOT NULL,
                                       descriere VARCHAR(255),
                                       pret DECIMAL(10, 2),
                                       cantitate_in_stoc INT,
                                       specificatii_tehnice VARCHAR(500)
);

-- Table for "angajati"
CREATE TABLE IF NOT EXISTS angajati (
                                        id SERIAL PRIMARY KEY,
                                        nume VARCHAR(100) NOT NULL,
                                        prenume VARCHAR(100) NOT NULL,
                                        functie VARCHAR(100),
                                        salariu DECIMAL(10, 2),
                                        data_angajarii DATE
);

-- Table for "clienti"
CREATE TABLE IF NOT EXISTS clienti (
                                       id SERIAL PRIMARY KEY,
                                       nume VARCHAR(100) NOT NULL,
                                       prenume VARCHAR(100) NOT NULL,
                                       adresa VARCHAR(200),
                                       email VARCHAR(100),
                                       telefon VARCHAR(20)
);

-- Table for "vanzari"
CREATE TABLE IF NOT EXISTS vanzari (
                                       id SERIAL PRIMARY KEY,
                                       data_vanzare DATE,
                                       client_id INT,
                                       angajat_id INT,
                                       produs_id INT,
                                       FOREIGN KEY (client_id) REFERENCES clienti(id),
                                       FOREIGN KEY (angajat_id) REFERENCES angajati(id),
                                       FOREIGN KEY (produs_id) REFERENCES produse(id)
);
