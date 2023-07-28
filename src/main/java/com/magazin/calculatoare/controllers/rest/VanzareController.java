package com.magazin.calculatoare.controllers.rest;

import com.magazin.calculatoare.dtos.VanzareDTO;
import com.magazin.calculatoare.services.VanzareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vanzari")
public class VanzareController {
    private final VanzareService vanzareService;

    @Autowired
    public VanzareController(VanzareService vanzareService) {
        this.vanzareService = vanzareService;
    }

    @GetMapping
    public ResponseEntity<List<VanzareDTO>> getAllVanzari() {
        List<VanzareDTO> vanzari = vanzareService.getAllVanzari();
        return new ResponseEntity<>(vanzari, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VanzareDTO> getVanzareById(@PathVariable Long id) {
        VanzareDTO vanzare = vanzareService.getVanzareById(id);
        if (vanzare != null) {
            return new ResponseEntity<>(vanzare, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VanzareDTO> saveVanzare(@RequestBody VanzareDTO vanzareDTO) {
        VanzareDTO savedVanzare = vanzareService.saveVanzare(vanzareDTO);
        return new ResponseEntity<>(savedVanzare, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VanzareDTO> updateVanzare(@PathVariable Long id, @RequestBody VanzareDTO vanzareDTO) {
        VanzareDTO existingVanzare = vanzareService.getVanzareById(id);
        if (existingVanzare != null) {
            vanzareDTO.setId(id);
            VanzareDTO updatedVanzare = vanzareService.saveVanzare(vanzareDTO);
            return new ResponseEntity<>(updatedVanzare, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVanzare(@PathVariable Long id) {
        VanzareDTO existingVanzare = vanzareService.getVanzareById(id);
        if (existingVanzare != null) {
            vanzareService.deleteVanzareById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

