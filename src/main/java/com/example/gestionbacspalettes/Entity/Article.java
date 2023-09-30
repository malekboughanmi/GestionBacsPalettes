package com.example.gestionbacspalettes.Entity;


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
public class Article extends Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArticle")
    private Long idArticle;

    private String Code;

    @OneToOne
    private Mouvement mouvement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Famille famille;

}
