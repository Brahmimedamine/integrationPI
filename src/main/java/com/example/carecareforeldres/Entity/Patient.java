package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpatient;
    private Integer user;
    @Enumerated(EnumType.STRING)
    private TypePatient typatient;
    private Boolean archiver;
    private Float poid;
    private Float longueur;
    private LocalDate datedeNais;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
    @JsonIgnore
    List<Repas> repas;


    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "patients")
    @JsonIgnore
    List<Maladie> maladies=new ArrayList<>();



}
