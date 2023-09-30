package com.example.gestionbacspalettes.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mouvement extends Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMvt;

    private String Cmd;
    private String Bl;
    private String Dc;
    private String Post;
    private String Consigne;
    private String Quantite;
    private String Dlc;


    private String CodeMvt;

    private String US;
    private String MessageTraitement;
    private int NbEssai;
    private String Mag;
    private LocalDate DateCreation_Ot;
    private LocalDate DateTraitement_Ot;
    private String StatusTraitement_Ot;

    @OneToOne
    @JoinColumn(name = "id_Article")
    private Article article;


    @OneToOne
    @JoinColumn(name = "id_Fournisseur")
    private Fournisseur fournisseur;


    public Mouvement(String code_mvt, String bl , String quantite ,String cmd ) {
        this.CodeMvt = code_mvt;
        this.Bl = bl;
        this.Quantite = quantite;
        this.Cmd = cmd;
    }

    public Mouvement( String bl , String quantite ,String cmd ) {
        this.Bl = bl;
        this.Quantite = quantite;
        this.Cmd = cmd;
    }

    public Mouvement( String bl , String quantite) {
        this.Bl = bl;
        this.Quantite = quantite;
    }


}
