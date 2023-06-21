package com.exampleaval2.amazonas.aval2.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.springframework.http.MediaType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import com.exampleaval2.amazonas.aval2.models.Episode;
import com.exampleaval2.amazonas.aval2.models.EpisodeDetailsDTO;
import com.exampleaval2.amazonas.aval2.services.EpisodeService;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/episodes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class EpisodeController {
    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEpisode(@PathVariable int id, HttpServletResponse response) throws IOException {
        EpisodeDetailsDTO episodeDetails = episodeService.getEpisodeDetails(id);

        if (episodeDetails != null) {
            // Generar el PDF
            byte[] pdfBytes = generatePdf(episodeDetails);

            // Establecer las cabeceras para la respuesta PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename("episode.pdf").build());

            // Descargar el PDF como respuesta
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private byte[] generatePdf(EpisodeDetailsDTO episodeDetails) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("ID: " + episodeDetails.getId());
            contentStream.newLine();
            contentStream.showText("Nombre: " + episodeDetails.getName());
            contentStream.showText("Episodio: " + episodeDetails.getEpisode());
            // contentStream.showText("Fecha de consulta: " + episodeDetails.getDate());

            // Agregar más información del episodio si es necesario
            contentStream.endText();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        return baos.toByteArray();
    }
}
