package com.magazin.calculatoare.controllers.rest;

import com.magazin.calculatoare.dtos.AngajatDTO;

import com.magazin.calculatoare.services.AngajatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/angajati")
public class AngajatController {
    private final AngajatService angajatService;

    @Autowired
    public AngajatController(AngajatService angajatService) {
        this.angajatService = angajatService;
    }

    @GetMapping
    public ResponseEntity<List<AngajatDTO>> getAllAngajati(
            @RequestParam(name = "nume", required = false) String nume,
            @RequestParam(name = "prenume", required = false) String prenume,
            @RequestParam(name = "functie", required = false) String functie,
            @RequestParam(name = "salariuMin", required = false) Double salariuMin,
            @RequestParam(name = "salariuMax", required = false) Double salariuMax,
            @RequestParam(name = "dataAngajariiMin", required = false) LocalDate dataAngajariiMin,
            @RequestParam(name = "dataAngajariiMax", required = false) LocalDate dataAngajariiMax
    ) {
        List<AngajatDTO> angajati =
                angajatService.getAllAngajati(nume, prenume, functie, salariuMin, salariuMax, dataAngajariiMin, dataAngajariiMax);
        return new ResponseEntity<>(angajati, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AngajatDTO> getAngajatById(@PathVariable Long id) {
        AngajatDTO angajat = angajatService.getAngajatById(id);
        if (angajat != null) {
            return new ResponseEntity<>(angajat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AngajatDTO> saveAngajat(@RequestBody AngajatDTO angajatDTO) {
        AngajatDTO savedAngajat = angajatService.saveAngajat(angajatDTO);
        return new ResponseEntity<>(savedAngajat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AngajatDTO> updateAngajat(@PathVariable Long id, @RequestBody AngajatDTO angajatDTO) {
        AngajatDTO existingAngajat = angajatService.getAngajatById(id);
        if (existingAngajat != null) {
            angajatDTO.setId(id);
            AngajatDTO updatedAngajat = angajatService.saveAngajat(angajatDTO);
            return new ResponseEntity<>(updatedAngajat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAngajat(@PathVariable Long id) {
        AngajatDTO existingAngajat = angajatService.getAngajatById(id);
        if (existingAngajat != null) {
            angajatService.deleteAngajatById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

