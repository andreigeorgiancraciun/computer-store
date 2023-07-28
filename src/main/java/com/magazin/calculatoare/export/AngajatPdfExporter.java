package com.magazin.calculatoare.export;

import com.magazin.calculatoare.entities.Angajat;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class AngajatPdfExporter {
    public void exportToPdf(List<Angajat> angajati, String filePath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        int yPosition = 700;
        for (Angajat angajat : angajati) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.moveTextPositionByAmount(100, yPosition);
            contentStream.drawString(angajat.getNume());
            contentStream.moveTextPositionByAmount(100, 0);
            contentStream.drawString(angajat.getPrenume());
            contentStream.moveTextPositionByAmount(100, 0);
            contentStream.drawString(angajat.getFunctie());
            // Adaugă alte detalii ale angajatului, după cum dorești
            contentStream.endText();

            yPosition -= 20;
        }

        contentStream.close();

        document.save(new File(filePath));
        document.close();
    }
}
