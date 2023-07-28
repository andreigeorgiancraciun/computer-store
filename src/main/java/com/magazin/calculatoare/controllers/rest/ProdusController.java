package com.magazin.calculatoare.controllers.rest;

import com.magazin.calculatoare.dtos.ProdusDTO;
import com.magazin.calculatoare.services.ProdusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/produse")
public class ProdusController {
    private final ProdusService produsService;

    @Autowired
    public ProdusController(ProdusService produsService) {
        this.produsService = produsService;
    }

    @GetMapping
    public ResponseEntity<List<ProdusDTO>> getAllProducts() {
        List<ProdusDTO> products = produsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdusDTO> getProductById(@PathVariable Long id) {
        ProdusDTO product = produsService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProdusDTO> saveProduct(@RequestBody ProdusDTO produsDTO) {
        ProdusDTO savedProduct = produsService.saveProduct(produsDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdusDTO> updateProduct(@PathVariable Long id, @RequestBody ProdusDTO product) {
        ProdusDTO existingProduct = produsService.getProductById(id);
        if (existingProduct != null) {
            product.setId(id);
            ProdusDTO updatedProduct = produsService.saveProduct(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        ProdusDTO existingProduct = produsService.getProductById(id);
        if (existingProduct != null) {
            produsService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

