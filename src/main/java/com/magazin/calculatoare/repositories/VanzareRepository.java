package com.magazin.calculatoare.repositories;

import com.magazin.calculatoare.entities.Vanzare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanzareRepository extends JpaRepository<Vanzare, Long> {
}
