package com.example.gestionbacspalettes.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupeFournisseur implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroupe;

    private String groupeName;

    @OneToMany(mappedBy="groupeFournisseur", cascade = CascadeType.ALL)
    private Set<Fournisseur> fournisseurs;
}
