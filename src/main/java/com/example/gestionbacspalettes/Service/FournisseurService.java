package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.GroupeFournisseur;
import com.example.gestionbacspalettes.Repository.IFournisseurRepository;
import com.example.gestionbacspalettes.Repository.IGroupeFournisseurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FournisseurService implements  IFournisseurService {

    @Autowired
    IFournisseurRepository iFournisseurRepository;

    @Autowired
    IGroupeFournisseurRepository iGroupeFournisseurRepository;


    public String addfournisseur(Fournisseur f) {

        iFournisseurRepository.save(f);
        return("er");
    }


    @Override
    public void mettreAJourFournisseur(Long id, Fournisseur fournisseur) {
        if (iFournisseurRepository.existsById(id)) {
            fournisseur.setIdFournisseur(id);
            iFournisseurRepository.save(fournisseur);
        }
    }

    @Override
    public void assign(Fournisseur F,int idGroupe) {
        GroupeFournisseur groupeFournisseur= iGroupeFournisseurRepository.findById((long) idGroupe).orElse(null);
        F.setGroupeFournisseur(groupeFournisseur);
        iFournisseurRepository.save(F);
    }

    @Override
    public List<GroupeFournisseur> retreive1() {
        return iGroupeFournisseurRepository.findAll();
    }
}
