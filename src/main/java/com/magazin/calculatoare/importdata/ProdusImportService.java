package com.magazin.calculatoare.importdata;

import com.magazin.calculatoare.entities.Produs;
import com.magazin.calculatoare.entities.Vanzare;
import com.magazin.calculatoare.repositories.AngajatRepository;
import com.magazin.calculatoare.repositories.ClientRepository;
import com.magazin.calculatoare.repositories.ProdusRepository;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdusImportService {

    private final ProdusRepository produsRepository;
    private final ClientRepository clientRepository;
    private final AngajatRepository angajatRepository;

    public ProdusImportService(ProdusRepository produsRepository,
                               ClientRepository clientRepository,
                               AngajatRepository angajatRepository) {
        this.produsRepository = produsRepository;
        this.clientRepository = clientRepository;
        this.angajatRepository = angajatRepository;
    }

    public List<Produs> readDataFromXlsxFile(InputStream fileInputStream) throws IOException {
        List<Produs> produsList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Presupunem că datele sunt în prima foaie de calcul

            for (Row row : sheet) {
                String nume = row.getCell(0).getStringCellValue();
                String descriere = row.getCell(1).getStringCellValue();
                double pret = row.getCell(2).getNumericCellValue();
                int cantitateInStoc = (int) row.getCell(3).getNumericCellValue();
                String specificatiiTehnice = row.getCell(4).getStringCellValue();

                Produs produs = new Produs();
                produs.setNume(nume);
                produs.setDescriere(descriere);
                produs.setPret(pret);
                produs.setCantitateInStoc(cantitateInStoc);
                produs.setSpecificatiiTehnice(specificatiiTehnice);

                // Handle vanzari data (if available in the XLSX file)
                List<Vanzare> vanzariList = new ArrayList<>();

                // Get the sales data for the current product from the XLSX file
                LocalDate dataVanzare = row.getCell(5).getLocalDateTimeCellValue().toLocalDate();
                Long clientId = (long) row.getCell(6).getNumericCellValue();
                Long angajatId = (long) row.getCell(7).getNumericCellValue();
                Long produsId = (long) row.getCell(8).getNumericCellValue();

                // Create Vanzare object and set its properties
                Vanzare vanzare = new Vanzare();
                vanzare.setDataVanzare(dataVanzare);
                vanzare.setClient(clientRepository.findById(clientId).orElse(null));
                vanzare.setAngajat(angajatRepository.findById(angajatId).orElse(null));
                vanzare.setProdus(produsRepository.findById(produsId).orElse(null));

                // Add the Vanzare object to the vanzariList
                vanzariList.add(vanzare);

                // Set the vanzariList for the product
                produs.setVanzari(vanzariList);

                // Add the product to the productList
                produsList.add(produs);
            }
        }

        return produsList;
    }

    public void saveProduse(List<Produs> produsList) {
        produsRepository.saveAll(produsList);
    }
}
