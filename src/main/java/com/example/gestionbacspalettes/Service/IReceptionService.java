package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Mouvement;

import java.util.List;

public interface IReceptionService {




    public Mouvement modifierMouvement(Long mouvementId, String cmd, String bl, String quantite, String codeMvt, String a, String f);

    public Mouvement ajouterMvt(Mouvement mouvement);
    public List<String> getAllNomsFournisseurs();

    public List<Mouvement> getMouvementsWithSpecificAttributes();

    public int getMouvementByCodeMvt(String codeMvt);

    public Mouvement findMouvementById(Long id);



}
