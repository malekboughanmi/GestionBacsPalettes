package com.example.gestionbacspalettes.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Fournisseur extends Audit implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFournisseur")
    private Long idFournisseur;

    private String nom;
    @Email

    private String email;

    @ManyToOne
    private GroupeFournisseur groupeFournisseur;

    @OneToOne
    private Mouvement mvt;

}
