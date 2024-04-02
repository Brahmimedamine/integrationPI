package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Homeless;
import com.example.carecareforeldres.Repository.HomelessRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class HomelessService implements IHomelessService{
    HomelessRepository homelessRepository ;
    @Override
    public List<Homeless> retrieveAllHomeless() {
        return homelessRepository.findAll();
    }

    @Override
    public Homeless addHomeless(Homeless s) {
        return homelessRepository.save(s);
    }

    @Override
    public Homeless updateHomeless(Homeless s) {
        return homelessRepository.save(s);
    }

    @Override
    public Homeless retrieveHomeless(Long idHomeless) {
        return homelessRepository.findById(idHomeless).get();
    }

    @Override
    public void removeHomeless(Long idHomeless) {
        homelessRepository.deleteById(idHomeless);
    }


    // recherhceHomelessAvecSaLocation(String location):

    //  recherhcelessByAgeGreaterThan(Integer age):
    //  recherhcelessBySituationMedicale(String situation):


    //public double calculatePercentageOfHomelessWithMedicalNeeds();
    //public service ajoutServiceSpecifiqueSelonHomlessNeeds();
    //public addHomlessAndAssingToShelterWithCapacityCondititon() ;
    // public nbrehomlessDansChaqueSheletrParTypeServie ;
}

