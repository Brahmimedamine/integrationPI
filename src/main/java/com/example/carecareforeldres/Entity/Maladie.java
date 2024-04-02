package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Maladie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "maladies")
    @JsonIgnore
    private List<Ingredient> ingredients=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Patient> patients=new ArrayList<>();


}
