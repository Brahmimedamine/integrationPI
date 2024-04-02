package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Maladie;
import com.example.carecareforeldres.Repository.MaladieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaladieService implements IServiceMaladie{

    MaladieRepository maladieRepository ;

    @Override
    public Maladie add(Maladie res) {return maladieRepository.save(res);}
    @Override
    public List<Maladie> getAll(){return maladieRepository.findAll();}

    @Override
    public void remove(int idf) {
        maladieRepository.deleteById(idf);}

    @Override
    public Maladie update(Maladie res) {
        return maladieRepository.save(res);
    }
}
