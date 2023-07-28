-- Datele pentru Produse
INSERT INTO produse (nume, descriere, pret, cantitate_in_stoc, specificatii_tehnice)
SELECT 'Laptop', 'Laptop performant cu SSD', 3000, 5, 'Procesor i7, RAM 16GB, SSD 512GB'
WHERE NOT EXISTS (
    SELECT 1 FROM produse WHERE nume = 'Laptop'
) LIMIT 1;

INSERT INTO produse (nume, descriere, pret, cantitate_in_stoc, specificatii_tehnice)
SELECT 'Monitor', 'Monitor 24 inch Full HD', 500, 10, 'Rezoluție 1920x1080, refresh rate 60Hz'
WHERE NOT EXISTS (
    SELECT 1 FROM produse WHERE nume = 'Monitor'
) LIMIT 1;

INSERT INTO produse (nume, descriere, pret, cantitate_in_stoc, specificatii_tehnice)
SELECT 'Tastatura', 'Tastatura mecanică pentru gaming', 200, 15, 'Switch-uri Cherry MX Red, iluminare RGB'
WHERE NOT EXISTS (
    SELECT 1 FROM produse WHERE nume = 'Tastatura'
) LIMIT 1;

INSERT INTO produse (nume, descriere, pret, cantitate_in_stoc, specificatii_tehnice)
SELECT 'Mouse', 'Mouse optic pentru gaming', 80, 20, 'Senzor 16000 DPI, 7 butoane programabile'
WHERE NOT EXISTS (
    SELECT 1 FROM produse WHERE nume = 'Mouse'
) LIMIT 1;

-- Datele pentru Angajati
INSERT INTO angajati (nume, prenume, functie, salariu, data_angajarii)
SELECT 'John', 'Doe', 'Sales Representative', 3000, '2023-07-01'
WHERE NOT EXISTS (
    SELECT 1 FROM angajati WHERE nume = 'John' AND prenume = 'Doe'
) LIMIT 1;

INSERT INTO angajati (nume, prenume, functie, salariu, data_angajarii)
SELECT 'Alice', 'Smith', 'Manager', 4000, '2022-12-15'
WHERE NOT EXISTS (
    SELECT 1 FROM angajati WHERE nume = 'Alice' AND prenume = 'Smith'
) LIMIT 1;

INSERT INTO angajati (nume, prenume, functie, salariu, data_angajarii)
SELECT 'Bob', 'Johnson', 'Technician', 2500, '2023-02-28'
WHERE NOT EXISTS (
    SELECT 1 FROM angajati WHERE nume = 'Bob' AND prenume = 'Johnson'
) LIMIT 1;

-- Datele pentru Clienti
INSERT INTO clienti (nume, prenume, adresa, email, telefon)
SELECT 'Jane', 'Doe', '456 Park Ave', 'jane@example.com', '555-5678'
WHERE NOT EXISTS (
    SELECT 1 FROM clienti WHERE nume = 'Jane' AND prenume = 'Doe'
) LIMIT 1;

INSERT INTO clienti (nume, prenume, adresa, email, telefon)
SELECT 'Michael', 'Brown', '789 Elm St', 'michael@example.com', '555-9876'
WHERE NOT EXISTS (
    SELECT 1 FROM clienti WHERE nume = 'Michael' AND prenume = 'Brown'
) LIMIT 1;

INSERT INTO clienti (nume, prenume, adresa, email, telefon)
SELECT 'Sarah', 'Lee', '321 Maple Rd', 'sarah@example.com', '555-2468'
WHERE NOT EXISTS (
    SELECT 1 FROM clienti WHERE nume = 'Sarah' AND prenume = 'Lee'
) LIMIT 1;

-- Inserare date in tabela "vanzari" cu referinte catre cheile externe
-- Inserare date in tabela "vanzari" cu referinte catre cheile externe
INSERT INTO vanzari (data_vanzare, client_id, angajat_id, produs_id)
SELECT '2023-07-10', 1, 1, 2
WHERE NOT EXISTS (
    SELECT 1 FROM vanzari WHERE client_id = 1 AND angajat_id = 1 AND produs_id = 2
) LIMIT 1;

INSERT INTO vanzari (data_vanzare, client_id, angajat_id, produs_id)
SELECT '2023-07-15', 2, 2, 1
WHERE NOT EXISTS (
    SELECT 1 FROM vanzari WHERE client_id = 2 AND angajat_id = 2 AND produs_id = 1
) LIMIT 1;

INSERT INTO vanzari (data_vanzare, client_id, angajat_id, produs_id)
SELECT '2023-07-20', 3, 3, 3
WHERE NOT EXISTS (
    SELECT 1 FROM vanzari WHERE client_id = 3 AND angajat_id = 3 AND produs_id = 3
) LIMIT 1;

