package com.example.gestionbacspalettes.Controller;

import com.example.gestionbacspalettes.Entity.Article;
import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.Mouvement;
import com.example.gestionbacspalettes.Repository.IArticleRepository;
import com.example.gestionbacspalettes.Repository.IFournisseurRepository;
import com.example.gestionbacspalettes.Repository.IMouvementRepository;
import com.example.gestionbacspalettes.Service.EmailService;
import com.example.gestionbacspalettes.Service.IReceptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/Reception")
public class ReceptionController {


    IReceptionService iReceptionService;

    IArticleRepository iArticleRepository;
    IFournisseurRepository iFournisseurRepository;

    IMouvementRepository iMouvementRepository;


    private final EmailService emailService;




    @PutMapping("/modifierMouvement/{mouvementId}")
    public ResponseEntity<String> modifierMouvement(@PathVariable Long mouvementId, @RequestBody Mouvement mouvementRequest) {
        try {
            String cmd = mouvementRequest.getCmd();
            String bl = mouvementRequest.getBl();
            String quantite = mouvementRequest.getQuantite();
            String codeMvt = mouvementRequest.getCodeMvt();
            String a = String.valueOf(mouvementRequest.getArticle());
            String f = String.valueOf(mouvementRequest.getFournisseur());

            Mouvement mouvement = iReceptionService.modifierMouvement(mouvementId, cmd, bl, quantite, codeMvt, a, f);

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
            mouvement.setCmd(mouvement.getCmd());
            mouvement.setCodeMvt("542");
            mouvement.setQuantite(mouvement.getQuantite());

             iReceptionService.ajouterMvt(mouvement);
        } else {
            throw new RuntimeException("L'article et/ou le fournisseur n'existent pas.");
        }
        String to = fournisseur.getEmail();
        String subject = "Notification de reception de bacs plastiques";
        String text = "FOURNISSEUR :  " + fournisseur.getNom() + ",\n\nNous avons effectuer la reception ...";

        emailService.sendEmail(to, subject, text);
    }


    @GetMapping("/fournisseurs/noms")
    public List<String> getAllNomsFournisseurs() {
        return iReceptionService.getAllNomsFournisseurs();
    }


    @GetMapping("/mvt/all")
    public List<Mouvement> getAllMvtWithAttributes() {
        return iMouvementRepository.findAllMvtWithAttributes();
    }


    @GetMapping("/mouvements/save")
    public Mouvement save(@RequestBody Mouvement mvt) {
        return iMouvementRepository.save(mvt);
    }






    @GetMapping("/mouvements/get")
    public ResponseEntity<List<Mouvement>> getMouvementsWithSpecificAttributes() {
        List<Mouvement> mouvements = iReceptionService.getMouvementsWithSpecificAttributes();
        return ResponseEntity.ok(mouvements);
    }

    @GetMapping("/Recherche/{codeMvt}")
    public ResponseEntity<Integer> getMouvementByCodeMvt(@PathVariable String codeMvt) {
        int id = iReceptionService.getMouvementByCodeMvt(codeMvt);
      return  ResponseEntity.ok(id);
    }


    @GetMapping("/GetById/{id}")
    public Mouvement getMouvementById(@PathVariable Long id) {
        return iReceptionService.findMouvementById(id);
    }



}




