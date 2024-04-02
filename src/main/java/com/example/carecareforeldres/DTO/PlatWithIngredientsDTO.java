package com.example.carecareforeldres.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class PlatWithIngredientsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlat;
    private String nomPlat;
    private float prixPlat;
    private String descPlat;
    private String image;
    private  Integer CuisinierId;


    private List<Integer> ingredientIds =new ArrayList<>();



}
