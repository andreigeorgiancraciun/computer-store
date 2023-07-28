package com.magazin.calculatoare.repositories;

import com.magazin.calculatoare.entities.Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdusRepository extends JpaRepository<Produs, Long> {
}
