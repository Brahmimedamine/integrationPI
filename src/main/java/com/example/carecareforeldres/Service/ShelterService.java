package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Repository.AideRepository;
import com.example.carecareforeldres.Repository.ShelterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ShelterService implements IShelterService{
    ShelterRepository shelterRepository;
    AideRepository aideRepository;


    @Override
    public List<Shelter> retrieveAllShelter() {
        return shelterRepository.findAll();
    }

    @Override
    public Shelter addShelter(Shelter s) {
        return shelterRepository.save(s);
    }

    @Override
    public Shelter updateShelter(Shelter s) {
        return shelterRepository.save(s);
    }

    @Override
    public Shelter retrieveShelter(Long idShelter) {
        return shelterRepository.findById(idShelter).get();
    }

    @Override
    public void removeShelter(Long idShelter) {
        shelterRepository.deleteById(idShelter);
    }

    @Override
    public Shelter assignAideToShelter(Long idAide,Shelter shelter) {
        Aide aide = aideRepository.findById(idAide).get();
        shelter.getAides().add(aide);
        shelterRepository.save(shelter);

        return shelter;
    }
}
