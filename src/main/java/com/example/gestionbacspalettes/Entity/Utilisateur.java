package com.example.gestionbacspalettes.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Utilisateur extends Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String nom;
    private String matricule;

    @Email
    private String email;

    private String password;


    @ManyToMany
    private Set<Groupe> groupes;



}
