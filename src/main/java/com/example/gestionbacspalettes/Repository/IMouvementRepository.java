package com.example.gestionbacspalettes.Repository;

import com.example.gestionbacspalettes.Entity.Article;
import com.example.gestionbacspalettes.Entity.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMouvementRepository extends JpaRepository<Mouvement,Long> {

    @Query("SELECT DISTINCT m FROM Mouvement m GROUP BY m.idMvt")
    List<Mouvement> findAllDistinct();

    @Query("SELECT m.idMvt , m.article , m.fournisseur FROM Mouvement m")
    List<Mouvement> findAllMvt();

    @Query("SELECT m FROM Mouvement m JOIN FETCH m.article JOIN FETCH m.fournisseur WHERE m.CodeMvt= '542'")
    List<Mouvement> findAllMvtWithAttributes();
    @Query("SELECT m FROM Mouvement m JOIN FETCH m.article JOIN FETCH m.fournisseur WHERE m.CodeMvt= '541'")
    List<Mouvement> findAllMvtWithAttributes1();
    @Query("SELECT m.Cmd, m.Bl, m.Quantite, m.CodeMvt FROM Mouvement m")
    List<Object[]> getMouvementsWithSpecificAttributes();


    @Query("SELECT m.idMvt AS id FROM Mouvement m WHERE m.CodeMvt = :codeMvt")
    int findMouvementByCodeMvt(@Param("codeMvt") String codeMvt);


    @Query("SELECT m FROM Mouvement m WHERE m.idMvt = :id")
    Mouvement findById(long id);


}
