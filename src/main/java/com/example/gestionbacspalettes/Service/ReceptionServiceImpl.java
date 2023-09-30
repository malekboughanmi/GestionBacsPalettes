package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.Mouvement;
import com.example.gestionbacspalettes.Repository.IArticleRepository;
import com.example.gestionbacspalettes.Repository.IFournisseurRepository;
import com.example.gestionbacspalettes.Repository.IMouvementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReceptionServiceImpl  implements  IReceptionService {

    @Autowired
    IMouvementRepository iMouvementRepository;

    @Autowired
    IArticleRepository iArticleRepository;

    @Autowired
    IFournisseurRepository iFournisseurRepository;





    public Mouvement modifierMouvement(Long mouvementId, String cmd, String bl, String quantite, String codeMvt, String a, String f) {
        Mouvement mouvement = iMouvementRepository.findById(mouvementId).orElse(null);

        if (mouvement == null) {
            throw new IllegalArgumentException("Mouvement non trouvé");
        }

        mouvement.setCmd(cmd);
        mouvement.setBl(bl);
        mouvement.setQuantite(quantite);
        mouvement.setCodeMvt(codeMvt);

        return iMouvementRepository.save(mouvement);
    }


    public Mouvement ajouterMvt(Mouvement mouvement) {

        return iMouvementRepository.save(mouvement);
    }


    public List<String> getAllNomsFournisseurs() {
        List<Fournisseur> fournisseurs = iFournisseurRepository.findAll();
        List<String> nomsFournisseurs = new ArrayList<>();
        for (Fournisseur fournisseur : fournisseurs) {
            nomsFournisseurs.add(fournisseur.getNom());
        }
        return nomsFournisseurs;
    }

    public List<Mouvement> getMouvementsWithSpecificAttributes() {

        List<Object[]> mouvements = iMouvementRepository.getMouvementsWithSpecificAttributes();

        List<Mouvement> mouvementDTOs = new ArrayList<>();


        for (Object[] mouvement : mouvements) {
            String bl = (String) mouvement[0];
            String cmd = (String) mouvement[1];
            String quantite = (String) mouvement[2];

            mouvementDTOs.add(new Mouvement(bl, cmd, quantite));
        }

        return mouvementDTOs;
    }


    public int getMouvementByCodeMvt(String codeMvt) {
        return iMouvementRepository.findMouvementByCodeMvt(codeMvt);
    }


    public Mouvement findMouvementById(Long id) {
        Mouvement mouvement = iMouvementRepository.findById(id).orElse(null);
        if (mouvement != null) {
            Mouvement mouvementDTO = new Mouvement();
            mouvementDTO.setCodeMvt(mouvement.getCodeMvt());
            mouvementDTO.setBl(mouvement.getBl());
            mouvementDTO.setQuantite(mouvement.getQuantite());
            mouvementDTO.setCmd(mouvement.getCmd());
            return mouvementDTO;
        }
        return null;
    }



}



