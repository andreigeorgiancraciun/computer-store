package com.magazin.calculatoare.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vanzari")
@Getter
@Setter
public class Vanzare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataVanzare;

    @ManyToOne
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JsonBackReference
    private Angajat angajat;

    @ManyToOne
    @JsonBackReference
    private Produs produs;
}
