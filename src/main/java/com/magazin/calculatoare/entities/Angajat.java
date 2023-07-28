package com.magazin.calculatoare.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "angajati")
@Getter
@Setter
public class Angajat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String prenume;
    private String functie;
    private double salariu;
    private LocalDate dataAngajarii;

    @OneToMany(mappedBy = "angajat", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vanzare> vanzari;
}
