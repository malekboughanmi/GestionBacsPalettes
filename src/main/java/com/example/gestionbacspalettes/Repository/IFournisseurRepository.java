package com.example.gestionbacspalettes.Repository;

import com.example.gestionbacspalettes.Entity.Article;
import com.example.gestionbacspalettes.Entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFournisseurRepository extends JpaRepository<Fournisseur,Long> {
    Fournisseur findByNom(String nom);
}

