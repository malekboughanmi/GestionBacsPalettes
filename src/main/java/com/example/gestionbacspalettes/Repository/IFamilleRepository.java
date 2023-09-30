package com.example.gestionbacspalettes.Repository;

import com.example.gestionbacspalettes.Entity.Article;
import com.example.gestionbacspalettes.Entity.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFamilleRepository extends JpaRepository<Famille,Long> {
}
