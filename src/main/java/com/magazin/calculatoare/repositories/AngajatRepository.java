package com.magazin.calculatoare.repositories;

import com.magazin.calculatoare.entities.Angajat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Long>, JpaSpecificationExecutor<Angajat> {
}
