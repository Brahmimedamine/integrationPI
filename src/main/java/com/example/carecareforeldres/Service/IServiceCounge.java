package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Counge;

import java.util.List;

public interface IServiceCounge {
    Counge add(Counge res);

    List<Counge> getAll();

    void remove(int idf);

    Counge update(Counge res);

    Counge DemandeCoungeCuisine(Counge counge, Integer CuisinierId);
}
