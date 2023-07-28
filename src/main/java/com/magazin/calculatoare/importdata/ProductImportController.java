package com.magazin.calculatoare.importdata;

import com.magazin.calculatoare.entities.Produs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductImportController {
    private final ProdusImportService produsImportService;

    public ProductImportController(ProdusImportService produsImportService) {
        this.produsImportService = produsImportService;
    }

    @PostMapping("/model/angajati/import-products")
    public ResponseEntity<String> importProducts(@RequestParam("file") MultipartFile file) {
        try {
            List<Produs> productList = produsImportService.readDataFromXlsxFile(file.getInputStream());
            produsImportService.saveProduse(productList);
            return ResponseEntity.ok("Importul produselor a fost realizat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("A apărut o eroare la importul produselor!");
        }
    }
}

