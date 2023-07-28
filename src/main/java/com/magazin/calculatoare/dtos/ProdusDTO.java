package com.magazin.calculatoare.dtos;

import com.magazin.calculatoare.entities.Vanzare;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProdusDTO {
    private Long id;
    private String nume;
    private String descriere;
    private double pret;
    private int cantitateInStoc;
    private String specificatiiTehnice;
    private List<Vanzare> vanzari;
}
