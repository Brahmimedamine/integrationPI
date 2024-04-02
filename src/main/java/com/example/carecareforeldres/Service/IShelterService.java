package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Shelter;

import java.util.List;

public interface IShelterService {
    List<Shelter> retrieveAllShelter();

    Shelter addShelter(Shelter s);

    Shelter updateShelter(Shelter s);

    Shelter retrieveShelter(Long idShelter);

    void removeShelter(Long idShelter);

    public Shelter assignAideToShelter(Long idAide, Shelter shelter);


}
