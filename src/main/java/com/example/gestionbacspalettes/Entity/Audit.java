package com.example.gestionbacspalettes.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

//@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAudit;

    private String createdBy;

    private String updatedBy;

    private LocalDate DateCreation;

    private LocalDate DateTraitement;




}
