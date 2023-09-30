package com.example.gestionbacspalettes.Controller;

import com.example.gestionbacspalettes.Entity.Article;
import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.Mouvement;
import com.example.gestionbacspalettes.Repository.IArticleRepository;
import com.example.gestionbacspalettes.Repository.IFournisseurRepository;
import com.example.gestionbacspalettes.Repository.IMouvementRepository;
import com.example.gestionbacspalettes.Service.EmailService;
import com.example.gestionbacspalettes.Service.IExpeditionService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/Expedition")
public class ExpeditionController {

    IExpeditionService iExpeditionService;

    IArticleRepository iArticleRepository;
    IFournisseurRepository iFournisseurRepository;

    IMouvementRepository iMouvementRepository;

    private final EmailService emailService;



    @PutMapping("/modifierMouvement/{mouvementId}")
    public ResponseEntity<String> modifierMouvement(@PathVariable Long mouvementId, @RequestBody Mouvement mouvementRequest) {
        try {

            String bl = mouvementRequest.getBl();
            String quantite = mouvementRequest.getQuantite();
            String codeMvt = mouvementRequest.getCodeMvt();
            String a = String.valueOf(mouvementRequest.getArticle());
            String f = String.valueOf(mouvementRequest.getFournisseur());

            Mouvement mouvement = iExpeditionService.modifierMouvement(mouvementId,  bl, quantite, codeMvt, a, f);

            return new ResponseEntity<>("Mouvement modifié avec succès", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la modification du mouvement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/AjoutMouvement/{articleId}/{fournisseurId}")
    public void ajouterMouvement(@PathVariable Long articleId, @PathVariable Long fournisseurId,  @RequestBody Mouvement mouvement) {

        Article article = iArticleRepository.findById(articleId).orElse(null);
        Fournisseur fournisseur = iFournisseurRepository.findById(fournisseurId).orElse(null);


        if (article != null && fournisseur != null) {
            mouvement.setArticle(article);
            mouvement.setFournisseur(fournisseur);
            mouvement.setBl(mouvement.getBl());
            mouvement.setCodeMvt("541");
            mouvement.setQuantite(mouvement.getQuantite());

            iExpeditionService.ajouterMvt(mouvement);
        }

 else {
            throw new RuntimeException("L'article et/ou le fournisseur n'existent pas.");
        }
        String to = fournisseur.getEmail();
        String subject = "Notification de Expedition de bacs plastiques";
        String text = "FOURNISSEUR :  " + fournisseur.getNom() +",\n\nNous avons effectuer la reception ...";

        emailService.sendEmail(to, subject, text);
    }


    @GetMapping("/fournisseurs/noms")
    public List<String> getAllNomsFournisseurs() {
        return iExpeditionService.getAllNomsFournisseurs();
    }


    @GetMapping("/mvt/all")
    public List<Mouvement> getAllMvtWithAttributes() {
        return iMouvementRepository.findAllMvtWithAttributes1();
    }


    @GetMapping("/mouvements/save")
    public Mouvement save(@RequestBody Mouvement mvt) {
        return iMouvementRepository.save(mvt);
    }






    @GetMapping("/mouvements/get")
    public ResponseEntity<List<Mouvement>> getMouvementsWithSpecificAttributes() {
        List<Mouvement> mouvements = iExpeditionService.getMouvementsWithSpecificAttributes();
        return ResponseEntity.ok(mouvements);
    }

    @GetMapping("/Recherche/{codeMvt}")
    public ResponseEntity<Integer> getMouvementByCodeMvt(@PathVariable String codeMvt) {
        int id = iExpeditionService.getMouvementByCodeMvt(codeMvt);
        return  ResponseEntity.ok(id);
    }


    @GetMapping("/GetById/{id}")
    public Mouvement getMouvementById(@PathVariable Long id) {
        return iExpeditionService.findMouvementById(id);
    }


}
