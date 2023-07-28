package com.magazin.calculatoare.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "produse")
@Getter
@Setter
public class Produs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String descriere;
    private double pret;
    private int cantitateInStoc;
    private String specificatiiTehnice;

    @OneToMany(mappedBy = "produs", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vanzare> vanzari;
}

