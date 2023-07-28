package com.magazin.calculatoare.controllers.model;

import com.magazin.calculatoare.dtos.VanzareDTO;
import com.magazin.calculatoare.services.VanzareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/model/vanzari")
public class ModelVanzariController {

    public static final String REDIRECT_MODEL_VANZARI = "redirect:/model/vanzari/get";
    private final VanzareService vanzareService;

    public ModelVanzariController(VanzareService vanzareService) {
        this.vanzareService = vanzareService;
    }

    @GetMapping("/get")
    public String getAllVanzari(Model model) {
        List<VanzareDTO> vanzari = vanzareService.getAllVanzari();
        model.addAttribute("vanzari", vanzari);
        return "vanzare"; // This will resolve to the vanzare.html template
    }

    @GetMapping("/delete")
    public String deleteVanzare(@RequestParam Long id) {
        VanzareDTO existingVanzare = vanzareService.getVanzareById(id);
        if (existingVanzare != null) {
            vanzareService.deleteVanzareById(id);
        }
        return REDIRECT_MODEL_VANZARI;
    }

    @GetMapping("/edit")
    public String showUpdateVanzarePage(@RequestParam Long id, Model model) {
        VanzareDTO existingVanzare = vanzareService.getVanzareById(id);
        if (existingVanzare != null) {
            model.addAttribute("vanzare", existingVanzare);
            return "updateVanzare";
        } else {
            return REDIRECT_MODEL_VANZARI; // Redirect to vanzare list if vanzare not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateVanzare(@PathVariable Long id, @ModelAttribute VanzareDTO vanzare) {
        VanzareDTO existingVanzare = vanzareService.getVanzareById(id);
        if (existingVanzare != null) {
            vanzare.setId(id); // Set the ID of the updated vanzare
            vanzareService.saveVanzare(vanzare);
        }
        return REDIRECT_MODEL_VANZARI; // Redirect to the vanzare list page after the update is done
    }

    @GetMapping("/create-form")
    public String showCreateVanzarePage(Model model) {
        // Add an empty vanzare object to the model to bind form data
        model.addAttribute("vanzare", new VanzareDTO());
        return "createVanzare";
    }

    @PostMapping("/create")
    public String createVanzare(@ModelAttribute("vanzare") VanzareDTO vanzare) {
        // Save the new vanzare to the database
        vanzareService.saveVanzare(vanzare);
        // Redirect to the vanzare list page after creating the vanzare
        return REDIRECT_MODEL_VANZARI;
    }
}

