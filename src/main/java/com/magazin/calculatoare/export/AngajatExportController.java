package com.magazin.calculatoare.export;

import com.magazin.calculatoare.entities.Angajat;
import com.magazin.calculatoare.repositories.AngajatRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@RestController
public class AngajatExportController {

    private final AngajatPdfExporter pdfExporter;
    private final AngajatXlsxExporter xlsxExporter;
    private final AngajatRepository angajatRepository;

    public AngajatExportController(AngajatPdfExporter pdfExporter,
                             AngajatXlsxExporter xlsxExporter,
                             AngajatRepository angajatRepository) {
        this.pdfExporter = pdfExporter;
        this.xlsxExporter = xlsxExporter;
        this.angajatRepository = angajatRepository;
    }

    @GetMapping("/angajati/export/pdf")
    public ResponseEntity<Resource> exportAngajatiToPdf() throws IOException {
        List<Angajat> angajati = angajatRepository.findAll();

        // Generare fișier PDF
        String pdfFilePath = "angajati.pdf";
        pdfExporter.exportToPdf(angajati, pdfFilePath);

        File pdfFile = new File(pdfFilePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + pdfFile.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfFile.length())
                .body(resource);
    }

    @GetMapping("/angajati/export/xlsx")
    public ResponseEntity<Resource> exportAngajatiToXlsx() throws IOException {
        List<Angajat> angajati = angajatRepository.findAll();

        // Generare fișier XLSX
        String xlsxFilePath = "angajati.xlsx";
        xlsxExporter.exportToXlsx(angajati, xlsxFilePath);

        File xlsxFile = new File(xlsxFilePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(xlsxFile));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + xlsxFile.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(xlsxFile.length())
                .body(resource);
    }

    @GetMapping("/angajati/generate-chart")
    public void generatePieChart(HttpServletResponse response) {
        // Fetch data from the repository
        List<Angajat> angajatList = angajatRepository.findAll();

        // Create a dataset with angajat data
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Angajat angajat : angajatList) {
            dataset.setValue(angajat.getNume(), angajat.getSalariu());
        }

        // Create the pie chart using the dataset
        JFreeChart chart = ChartFactory.createPieChart(
                "Salariu Chart", // Chart title
                dataset, // Dataset
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // Configure the plot to display percentage values
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}")); // Display the values as percentages

        // Export the chart as an image
        BufferedImage chartImage = chart.createBufferedImage(500, 300);

        // Set the response content type to PDF
        response.setContentType("application/pdf");
        // Set the Content-Disposition header to trigger the download
        response.setHeader("Content-Disposition", "attachment; filename=\"salariu_chart.pdf\"");

        try (OutputStream out = response.getOutputStream()) {
            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Convert the chart image to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(chartImage, "png", baos);
            byte[] chartImageBytes = baos.toByteArray();

            // Embed the chart image in the PDF
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, chartImageBytes, "chart");
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(pdImage, 100, 400, pdImage.getWidth(), pdImage.getHeight());
            contentStream.close();

            // Save the PDF to the output stream
            document.save(out);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}

