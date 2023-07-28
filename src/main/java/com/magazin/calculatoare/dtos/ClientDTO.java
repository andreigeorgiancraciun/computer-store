package com.magazin.calculatoare.dtos;

import com.magazin.calculatoare.entities.Vanzare;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String nume;
    private String prenume;
    private String adresa;
    private String email;
    private String telefon;
    private List<Vanzare> vanzari;
}
