package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.GroupeFournisseur;

import java.util.List;

public interface IFournisseurService {

    public String addfournisseur(Fournisseur f);

    void mettreAJourFournisseur(Long id, Fournisseur fournisseur);

    public void assign(Fournisseur F,int idGroupe);

    public List<GroupeFournisseur> retreive1();
}
