package com.magazin.calculatoare.controllers.model;

import com.magazin.calculatoare.dtos.ProdusDTO;
import com.magazin.calculatoare.services.ProdusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/model/produse")
public class ModelProdusController {

    public static final String REDIRECT_MODEL_PRODUSE = "redirect:/model/produse/get";
    private final ProdusService produsService;

    public ModelProdusController(ProdusService produsService) {
        this.produsService = produsService;
    }

    @GetMapping("/get")
    public String getAllProducts(Model model) {
        List<ProdusDTO> products = produsService.getAllProducts();
        model.addAttribute("products", products);
        return "product"; // This will resolve to the product.html template
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam Long id) {
        ProdusDTO existingProduct = produsService.getProductById(id);
        if (existingProduct != null) {
            produsService.deleteProductById(id);
        }
        return REDIRECT_MODEL_PRODUSE;
    }

    @GetMapping("/edit")
    public String showUpdateProductPage(@RequestParam Long id, Model model) {
        ProdusDTO existingProduct = produsService.getProductById(id);
        if (existingProduct != null) {
            model.addAttribute("product", existingProduct);
            return "updateProduct";
        } else {
            return REDIRECT_MODEL_PRODUSE; // Redirect to product list if product not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProdusDTO product) {
        ProdusDTO existingProduct = produsService.getProductById(id);
        if (existingProduct != null) {
            product.setId(id); // Set the ID of the updated product
            produsService.saveProduct(product);
        }
        return REDIRECT_MODEL_PRODUSE; // Redirect to the product list page after the update is done
    }

    @GetMapping("/create-form")
    public String showCreateProductPage(Model model) {
        // Add an empty product object to the model to bind form data
        model.addAttribute("product", new ProdusDTO());
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") ProdusDTO product) {
        // Save the new product to the database
        produsService.saveProduct(product);
        // Redirect to the product list page after creating the product
        return REDIRECT_MODEL_PRODUSE;
    }

}

