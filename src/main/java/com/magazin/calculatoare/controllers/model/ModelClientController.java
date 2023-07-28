package com.magazin.calculatoare.controllers.model;

import com.magazin.calculatoare.dtos.ClientDTO;
import com.magazin.calculatoare.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/model/clienti")
public class ModelClientController {

    public static final String REDIRECT_MODEL_CLIENTI = "redirect:/model/clienti/get";
    private final ClientService clientService;

    public ModelClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get")
    public String getAllClients(Model model) {
        List<ClientDTO> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "client"; // This will resolve to the client.html template
    }

    @GetMapping("/delete")
    public String deleteClient(@RequestParam Long id) {
        ClientDTO existingClient = clientService.getClientById(id);
        if (existingClient != null) {
            clientService.deleteClientById(id);
        }
        return REDIRECT_MODEL_CLIENTI;
    }

    @GetMapping("/edit")
    public String showUpdateClientPage(@RequestParam Long id, Model model) {
        ClientDTO existingClient = clientService.getClientById(id);
        if (existingClient != null) {
            model.addAttribute("client", existingClient);
            return "updateClient";
        } else {
            return REDIRECT_MODEL_CLIENTI; // Redirect to client list if client not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute ClientDTO client) {
        ClientDTO existingClient = clientService.getClientById(id);
        if (existingClient != null) {
            client.setId(id); // Set the ID of the updated client
            clientService.saveClient(client);
        }
        return REDIRECT_MODEL_CLIENTI; // Redirect to the client list page after the update is done
    }

    @GetMapping("/create-form")
    public String showCreateClientPage(Model model) {
        // Add an empty client object to the model to bind form data
        model.addAttribute("client", new ClientDTO());
        return "createClient";
    }

    @PostMapping("/create")
    public String createClient(@ModelAttribute("client") ClientDTO client) {
        // Save the new client to the database
        clientService.saveClient(client);
        // Redirect to the client list page after creating the client
        return REDIRECT_MODEL_CLIENTI;
    }
}


