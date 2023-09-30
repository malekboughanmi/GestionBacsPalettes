package com.example.gestionbacspalettes.Entity;


import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFamille;

    private String typeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="famille")
    private Set<Article> Articles;

}
