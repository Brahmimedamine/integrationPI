package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Cuisinier;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CuisinierService implements IServiceCuisinier{
    CuisinierRepository cuisinierRepository;

    @Override
    public Cuisinier add(Cuisinier res) {return cuisinierRepository.save(res);}
    @Override
    public List<Cuisinier> getAll(){return cuisinierRepository.findAll();}

    @Override
    public void remove(int idf) {
        cuisinierRepository.deleteById(idf);}

    @Override
    public Cuisinier update(Cuisinier res) {
        return cuisinierRepository.save(res);
    }
}
