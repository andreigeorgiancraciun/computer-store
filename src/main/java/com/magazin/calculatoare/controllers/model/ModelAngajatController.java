package com.magazin.calculatoare.controllers.model;

import com.magazin.calculatoare.dtos.AngajatDTO;
import com.magazin.calculatoare.services.AngajatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/model/angajati")
public class ModelAngajatController {

    public static final String REDIRECT_MODEL_ANGAJATI = "redirect:/model/angajati/get";
    private final AngajatService angajatService;

    public ModelAngajatController(AngajatService angajatService) {
        this.angajatService = angajatService;
    }

    @GetMapping("/get")
    public String getAllAngajati(Model model) {
        List<AngajatDTO> angajati = angajatService.getAllAngajati();
        model.addAttribute("angajati", angajati);
        return "angajat"; // This will resolve to the angajat.html template
    }

    @GetMapping("/delete")
    public String deleteAngajat(@RequestParam Long id) {
        AngajatDTO existingAngajat = angajatService.getAngajatById(id);
        if (existingAngajat != null) {
            angajatService.deleteAngajatById(id);
        }
        return REDIRECT_MODEL_ANGAJATI;
    }

    @GetMapping("/edit")
    public String showUpdateAngajatPage(@RequestParam Long id, Model model) {
        AngajatDTO existingAngajat = angajatService.getAngajatById(id);
        if (existingAngajat != null) {
            model.addAttribute("angajat", existingAngajat);
            return "updateAngajat";
        } else {
            return REDIRECT_MODEL_ANGAJATI; // Redirect to angajat list if angajat not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateAngajat(@PathVariable Long id, @ModelAttribute AngajatDTO angajat) {
        AngajatDTO existingAngajat = angajatService.getAngajatById(id);
        if (existingAngajat != null) {
            angajat.setId(id); // Set the ID of the updated angajat
            angajatService.saveAngajat(angajat);
        }
        return REDIRECT_MODEL_ANGAJATI; // Redirect to the angajat list page after the update is done
    }

    @GetMapping("/create-form")
    public String showCreateAngajatPage(Model model) {
        // Add an empty angajat object to the model to bind form data
        model.addAttribute("angajat", new AngajatDTO());
        return "createAngajat";
    }

    @PostMapping("/create")
    public String createAngajat(@ModelAttribute("angajat") AngajatDTO angajat) {
        // Save the new angajat to the database
        angajatService.saveAngajat(angajat);
        // Redirect to the angajat list page after creating the angajat
        return REDIRECT_MODEL_ANGAJATI;
    }

    @GetMapping("/search")
    public String getAllAngajatiByFilter(@RequestParam(name = "nume", required = false) String nume,
                                         @RequestParam(name = "prenume", required = false) String prenume,
                                         @RequestParam(name = "functie", required = false) String functie,
                                         @RequestParam(name = "salariuMin", required = false) Double salariuMin,
                                         @RequestParam(name = "salariuMax", required = false) Double salariuMax,
                                         @RequestParam(name = "dataAngajariiMin", required = false) LocalDate dataAngajariiMin,
                                         @RequestParam(name = "dataAngajariiMax", required = false) LocalDate dataAngajariiMax, Model model) {
        List<AngajatDTO> angajati = angajatService.getAllAngajati(nume, prenume, functie, salariuMin, salariuMax, dataAngajariiMin, dataAngajariiMax);
        model.addAttribute("angajati", angajati);
        return "angajat";
    }
}

