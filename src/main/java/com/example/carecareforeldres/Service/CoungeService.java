package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.*;
import com.example.carecareforeldres.Repository.CoungeRepository;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CoungeService implements IServiceCounge{
    CoungeRepository coungeRepository ;
    CuisinierRepository cuisinierRepository;
    @Override
    public Counge add(Counge res) {return coungeRepository.save(res);}
    @Override
    public List<Counge> getAll(){return coungeRepository.findAll();}

    @Override
    public void remove(int idf) {
        coungeRepository.deleteById(idf);}

    @Override
    public Counge update(Counge res) {
        return coungeRepository.save(res);
    }

    @Scheduled(cron = "0 0 11 ? * TUE")
    public void supprimerCongesRefuses() {
        List<Counge> congesRefuses = coungeRepository.findByEtatCounger(EtatCounger.REFUSER);
        coungeRepository.deleteAll(congesRefuses);
        for (Counge v:congesRefuses) {
            log.info("conger de l'id  "+v.getId()+ "   supprimer");
        }
    }

    @Override
    public Counge DemandeCoungeCuisine(Counge counge, Integer CuisinierId){

        Cuisinier cuisinier=cuisinierRepository.findById(CuisinierId).get();
        counge.setEtatCounger(EtatCounger.EN_COUR);
        counge.setCuisinierC(cuisinier);
        counge.setDateAjout(LocalDateTime.now());
        List<Counge> congesDuCuisinier = cuisinier.getCounges();

        boolean aPrisConge = aPrisCongeDerniersQuatreMois(congesDuCuisinier);


        LocalDate dateDebut = counge.getDateDebut(); // Convertir en LocalDate
        LocalDate dateFin = counge.getDateFin(); // Convertir en LocalDate
        LocalDate currentDate = LocalDate.now();
        int joursCongesCetteAnnee = coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate);
        int dureeNouvelleConge = (int) ChronoUnit.DAYS.between(dateDebut, dateFin); // Calculer la durée du congé directement avec les LocalDate

        long differenceEnJours = ChronoUnit.DAYS.between(currentDate, dateDebut);

        if (counge.getDateFin().isBefore(counge.getDateDebut()) ) {

            if (differenceEnJours < 3) {
                log.info("La date de début du congé doit être au moins 3 jours après la date actuelle.");
                return null;
            }
            log.info("La date de fin est antérieure à la date de début.");
            return null;
        }
        else {
            if (aPrisConge) {

                if(counge.getTypeCounger().equals(TypeCounger.COUNGER_MATERNITE) && cuisinier.getSexe().equals(Sexe.FEMME) && dureeNouvelleConge < 45 && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUNGER_MATERNITE) == 0){
                    return coungeRepository.save(counge);
                }
                if(counge.getTypeCounger().equals(TypeCounger.COUGER_PARENTALE) && cuisinier.getSexe().equals(Sexe.HOMME) && dureeNouvelleConge < 3 && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUGER_PARENTALE) == 0){
                    return coungeRepository.save(counge);
                }
                log.info("tu a déjà pris un congé récemment");
                return counge;
            }


            if (joursCongesCetteAnnee + dureeNouvelleConge > 30) {
                if(counge.getTypeCounger().equals(TypeCounger.COUNGER_MATERNITE)  && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUNGER_MATERNITE) == 0){
                  if (cuisinier.getSexe().equals(Sexe.FEMME) && dureeNouvelleConge < 45) {
                      return coungeRepository.save(counge);
                  }
                }
                if(counge.getTypeCounger().equals(TypeCounger.COUGER_PARENTALE) && cuisinier.getSexe().equals(Sexe.HOMME) && dureeNouvelleConge < 3 && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUGER_PARENTALE) == 0){
                    return coungeRepository.save(counge);
                }
                log.info("Le cuisinier aura plus de 30 jours de congé cette année après l'ajout de cette nouvelle demande de congé.");
                return null;
            }


            return coungeRepository.save(counge);
        }
    }
    public boolean aPrisCongeDerniersQuatreMois(List<Counge> congés) {
        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime ilYaQuatreMois = maintenant.minusMonths(2);

        for (Counge congé : congés) {
            LocalDate dateDebutConge = congé.getDateDebut();
            LocalDateTime dateDebutLocalDateTime = dateDebutConge.atStartOfDay(); // Convertir LocalDate en LocalDateTime en ajoutant une heure arbitraire

            if (dateDebutLocalDateTime.isAfter(ilYaQuatreMois) && dateDebutLocalDateTime.isBefore(maintenant)) {
                return true;
            }
        }

        return false;
    }

}
