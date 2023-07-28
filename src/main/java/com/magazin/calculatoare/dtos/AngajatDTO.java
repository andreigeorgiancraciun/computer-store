package com.magazin.calculatoare.dtos;

import com.magazin.calculatoare.entities.Vanzare;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AngajatDTO {
    private Long id;
    private String nume;
    private String prenume;
    private String functie;
    private double salariu;
    private LocalDate dataAngajarii;
    List<Vanzare> vanzari;
}
