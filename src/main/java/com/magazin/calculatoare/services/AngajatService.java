package com.magazin.calculatoare.services;

import com.magazin.calculatoare.dtos.AngajatDTO;
import com.magazin.calculatoare.mappers.AngajatMapper;
import com.magazin.calculatoare.entities.Angajat;
import com.magazin.calculatoare.repositories.AngajatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.magazin.calculatoare.repositories.AngajatiSpecification.*;

@Service
public class AngajatService {
    private final AngajatRepository angajatRepository;

    @Autowired
    public AngajatService(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    public List<AngajatDTO> getAllAngajati(String nume,
                                           String prenume,
                                           String functie,
                                           Double salariuMin,
                                           Double salariuMax,
                                           LocalDate dataAngajariiMin,
                                           LocalDate dataAngajariiMax) {
        Specification<Angajat> specification = Specification.where(null);

        if (nume != null) {
            specification = specification.and(numeContainsIgnoreCase(nume));
        }

        if (prenume != null) {
            specification = specification.and(prenumeContainsIgnoreCase(prenume));
        }

        if (functie != null) {
            specification = specification.and(functieContainsIgnoreCase(functie));
        }

        if (salariuMin != null || salariuMax != null) {
            if (salariuMin == null) {
                salariuMin = 0.0;
            }

            if (salariuMax == null) {
                salariuMax = Double.MAX_VALUE;
            }
            specification = specification.and(salariuBetween(salariuMin, salariuMax));
        }

        if (dataAngajariiMin != null && dataAngajariiMax != null) {
            specification = specification.and(dataAngajariiBetween(dataAngajariiMin, dataAngajariiMax));
        }

        List<Angajat> angajatList = angajatRepository.findAll(specification);

        return angajatList.stream()
                .map(AngajatMapper.INSTANCE::toDTO)
                .toList();
    }

    public List<AngajatDTO> getAllAngajati() {
        List<Angajat> angajatList = angajatRepository.findAll();

        return angajatList.stream()
                .map(AngajatMapper.INSTANCE::toDTO)
                .toList();
    }

    public AngajatDTO getAngajatById(Long id) {
        Angajat angajat = angajatRepository.findById(id).orElse(null);
        return AngajatMapper.INSTANCE.toDTO(angajat);
    }

    public AngajatDTO saveAngajat(AngajatDTO angajatDTO) {
        Angajat angajat = AngajatMapper.INSTANCE.toEntity(angajatDTO);
        return AngajatMapper.INSTANCE.toDTO(angajatRepository.save(angajat));
    }

    public void deleteAngajatById(Long id) {
        angajatRepository.deleteById(id);
    }
}

