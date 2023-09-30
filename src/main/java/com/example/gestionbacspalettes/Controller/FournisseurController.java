package com.example.gestionbacspalettes.Controller;


import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.GroupeFournisseur;
import com.example.gestionbacspalettes.Repository.IFournisseurRepository;
import com.example.gestionbacspalettes.Service.IFournisseurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/fournisseur")
public class FournisseurController {

    IFournisseurRepository  iFournisseurRepository;

    IFournisseurService iFournisseurService;

    @GetMapping("/GetAll")
    public List<Fournisseur> getFournisseurs() {
        return iFournisseurRepository.findAll();
    }



    @PostMapping("/Add")
    public String addfournisseur(@RequestBody Fournisseur F) {

        return  iFournisseurService.addfournisseur(F);

    }

    @PutMapping("/UpdateFournisseur/{id}")
    public void mettreAJourFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        iFournisseurService.mettreAJourFournisseur(id, fournisseur);
    }


    @PostMapping("/fournisseurGroup/{idGroupe}")
    public void assign(@RequestBody Fournisseur F, @PathVariable int idGroupe){
        iFournisseurService.assign(F,idGroupe);
    }


    @GetMapping("/All/Getgroup")
    public List<GroupeFournisseur> getAllMouvements1() {
        return iFournisseurService.retreive1();
    }

}
