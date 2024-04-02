package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.RepasAvecPlatsDTO;
import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Entity.Plat;
import com.example.carecareforeldres.Entity.Repas;
import com.example.carecareforeldres.Entity.TypeRepas;
import com.example.carecareforeldres.Repository.PatientRepository;
import com.example.carecareforeldres.Repository.PlatRepository;
import com.example.carecareforeldres.Repository.RepasRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RepasService implements IServiceRepas{

    RepasRepository repasRepository;
    PlatRepository platRepository;
    PatientRepository patientREpository;
    @Override
    public Repas addPlat(Repas pt) {

        return repasRepository.save(pt);
    }

    @Override
    public List<Repas> getAll() {
        return repasRepository.findAll();
    }

    @Override
    public void remove(int idf) {
        repasRepository.deleteById(idf);
    }

    @Override
    public Repas update(Repas res) {
        return repasRepository.save(res);
    }

    @Override
    public Repas addRepasAvecPlats(RepasAvecPlatsDTO repasDTO) {
        Repas repas = new Repas();
        repas.setDateRepas(LocalDate.now());
        repas.setTypeRepas(repasDTO.getTypeRepas());

        List<Plat> plats = new ArrayList<>();
        for (Integer platId : repasDTO.getPlatsIds()) {
            Plat plat = platRepository.findById(platId).orElse(null);
            if (plat != null) {
                plats.add(plat);
            }
        }
        repas.setPlats(plats);
        Patient patient = patientREpository.findById(repasDTO.getPatientId()).orElse(null);
        if (patient != null) {
            repas.setPatient(patient);
        }

        return repasRepository.save(repas);
    }



    @Transactional
    @Override
    public Repas AffecterRepasAUser(Repas repas, Integer idUser) {
        LocalDate dateActuelle = LocalDate.now();


        if (repas.getTypeRepas().equals(TypeRepas.PETIT_DEJEUNER)) {
            if (!repasRepository.existsByPatientIdAndTypeRepasAndDate(idUser, TypeRepas.PETIT_DEJEUNER, dateActuelle)) {
                log.info("Tu as déjà un repas de petit déjeuner pour aujourd'hui.");
            }else{
            LocalTime heureActuelle = LocalTime.now();
            if (heureActuelle.isAfter(LocalTime.of(6, 0)) && heureActuelle.isBefore(LocalTime.of(9, 0))) {
                Patient pt = patientREpository.findById(idUser).get();
                repas.setPatient(pt);
                return repasRepository.save(repas);
            } else {

                log.info("Le repas doit être entre 8h et 10h pour le petit déjeuner.");
            }
        }
        }
        else if (repas.getTypeRepas().equals(TypeRepas.DEJEUNER)) {
            if (!repasRepository.existsByPatientIdAndTypeRepasAndDate(idUser, TypeRepas.DEJEUNER, dateActuelle)) {
                log.info("Tu as déjà un repas de  déjeuner pour aujourd'hui.");
            }else{
            LocalTime heureActuelle = LocalTime.now();
            if (heureActuelle.isAfter(LocalTime.of(12, 0)) && heureActuelle.isBefore(LocalTime.of(13, 30))) {
                Patient pt = patientREpository.findById(idUser).get();
                repas.setPatient(pt);
                return repasRepository.save(repas);
            } else {

                log.info("Le repas doit être entre 12h et 13h:30min pour le  déjeuner.");
            }
        }
        }
        else if (repas.getTypeRepas().equals(TypeRepas.DINER)) {
            if (!repasRepository.existsByPatientIdAndTypeRepasAndDate(idUser, TypeRepas.DINER, dateActuelle)) {
                log.info("Tu as déjà un repas de diner pour aujourd'hui.");
            }else{
            LocalTime heureActuelle = LocalTime.now();
            if (heureActuelle.isAfter(LocalTime.of(18, 0)) && heureActuelle.isBefore(LocalTime.of(19, 30))) {
                Patient pt = patientREpository.findById(idUser).get();
                repas.setPatient(pt);
                return repasRepository.save(repas);
            } else {

                log.info("Le repas doit être entre 18h et 19h:30min pour le  diner.");
            }
        }
        }
        return repas;
    }




}
