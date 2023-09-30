package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.Mouvement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IExpeditionService {


    public Mouvement modifierMouvement(Long mouvementId, String cmd, String bl, String quantite, String codeMvt, String a);

    public Mouvement ajouterMvt(Mouvement mouvement);
    public List<String> getAllNomsFournisseurs();

    public List<Mouvement> getMouvementsWithSpecificAttributes();

    public int getMouvementByCodeMvt(String codeMvt);

    public Mouvement findMouvementById(Long id);


    }
