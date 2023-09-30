package com.example.gestionbacspalettes.Controller;

import com.example.gestionbacspalettes.Entity.Mouvement;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/Api")
public class PdfReception  {



    @PostMapping(value = "/GeneratePdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf() throws IOException, DocumentException, SQLException {

        // Create a document object
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        // Create a byte array output stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Create a PDF writer object
        PdfWriter.getInstance(document, baos);

        // Open the document
        document.open();



        Image logo = Image.getInstance("/src/main/resources/Logo.png");
        // Positionner le logo en haut à gauche de la page
        logo.setAbsolutePosition(20, 760);
        // Définir la largeur et la hauteur maximales
        float maxWidth = 60f;
        float maxHeight = 60f;
        // Redimensionner le logo pour qu'il s'adapte à la taille maximale
        logo.scaleToFit(maxWidth, maxHeight);


        // Créer une police personnalisée pour le titre
        Font fontTitle = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.RED);
        fontTitle.setSize(28);
        Paragraph paragraph = new Paragraph("Reception", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(30);


        // Ajouter le logo au document
        document.add(logo);
        // Add content to the document
        document.add(paragraph);
        // Ajouter un saut de ligne après le titre
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));


        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bacspalettes", "root", "");
        // Créer une requête pour récupérer les données de la table reception
        String query = "SELECT * FROM mouvement";
        PreparedStatement stmt = conn.prepareStatement(query);
        // Exécuter la requête et stocker les résultats dans un ResultSet
        ResultSet rs = stmt.executeQuery();
        // Parcourir les résultats du ResultSet et stocker les données dans une liste de reception
        List<Mouvement> receptions = new ArrayList<>();
        while (rs.next()) {
            String bl = rs.getString("bl");
            String quantite = rs.getString("quantite");
            String cmd = rs.getString("cmd");
            Mouvement reception = new Mouvement( bl, quantite, cmd );
            receptions.add(reception);
        }


        // Créer une table pour afficher les données des receptions
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        // Ajouter des en-têtes de colonne à la table
        PdfPCell cell2 = new PdfPCell(new Phrase("Bl"));
        PdfPCell cell3 = new PdfPCell(new Phrase("Quantité"));
        PdfPCell cell4 = new PdfPCell(new Phrase("Commande"));
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        // Ajouter les données des receptions à la table
        for (Mouvement reception : receptions) {
            table.addCell(reception.getBl());
            table.addCell(reception.getQuantite());
            table.addCell(reception.getCmd());
        }


        // Ajouter la table au document PDF
        document.add(table);

        // Fermer les ressources JDBC
        rs.close();
        stmt.close();
        conn.close();

        // Close the document
        document.close();

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "Reception.pdf");

        // Return the response entity with the PDF content
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}
