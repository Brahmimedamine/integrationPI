package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.PlatWithIngredientsDTO;
import com.example.carecareforeldres.Entity.Plat;

import java.util.List;

public interface IServicePlat {




    Plat addPlatDTO(PlatWithIngredientsDTO platDTO);

    Plat addPlatPatient(Plat pt, Integer p);

    List<Plat> getAll();

    void remove(int idf);

    Plat update(Plat res);








    Boolean testMaladie(Plat pt, Integer idPatient);

}
