package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlat;

    private String nomPlat;
    private String descPlat;
    private float prixPlat;
    private String image;
    private LocalDate datePlat;//date l'ajout plat délai


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Repas> repas;

    @Enumerated(EnumType.STRING)
    private List<TypePlat> typePlat;

    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;



    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Cuisinier cuisinier;
}
