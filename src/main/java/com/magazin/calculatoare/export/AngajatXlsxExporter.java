package com.magazin.calculatoare.export;

import com.magazin.calculatoare.entities.Angajat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class AngajatXlsxExporter {
    public void exportToXlsx(List<Angajat> angajati, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Angajati");

        int rowNumber = 0;
        for (Angajat angajat : angajati) {
            Row row = sheet.createRow(rowNumber++);
            int cellNumber = 0;
            row.createCell(cellNumber++).setCellValue(angajat.getNume());
            row.createCell(cellNumber++).setCellValue(angajat.getPrenume());
            row.createCell(cellNumber++).setCellValue(angajat.getFunctie());
            // Adaugă alte detalii ale angajatului, după cum dorești
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
    }
}
