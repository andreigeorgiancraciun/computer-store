package com.magazin.calculatoare.dtos;

import com.magazin.calculatoare.entities.Angajat;
import com.magazin.calculatoare.entities.Client;
import com.magazin.calculatoare.entities.Produs;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VanzareDTO {
    private Long id;
    private LocalDate dataVanzare;
    private Client client;
    private Angajat angajat;
    private Produs produs;
}
