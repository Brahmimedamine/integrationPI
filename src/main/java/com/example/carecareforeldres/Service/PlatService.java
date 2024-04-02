package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.PlatWithIngredientsDTO;
import com.example.carecareforeldres.Entity.*;
import com.example.carecareforeldres.Repository.IngredientRepository;
import com.example.carecareforeldres.Repository.PatientRepository;
import com.example.carecareforeldres.Repository.PlatRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PlatService implements IServicePlat {

    PlatRepository platRepository;
    IngredientRepository ingredientRepository;
    PatientRepository patientREpository;





    @Override
    public Plat addPlatDTO(PlatWithIngredientsDTO platDTO) {
        Plat plat = new Plat();
        plat.setNomPlat(platDTO.getNomPlat());
        plat.setDescPlat(platDTO.getDescPlat());
        plat.setPrixPlat(platDTO.getPrixPlat());
        plat.setImage(platDTO.getImage());
        plat.setDatePlat(LocalDate.now());
        List<Ingredient> ingredients = new ArrayList<>();
        for (Integer ingredientId : platDTO.getIngredientIds()) {
            Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
            if (ingredient != null) {
                ingredients.add(ingredient);
            }
        }
        plat.setIngredients((ingredients));

        return platRepository.save(plat);
    }




    @Override
    public Plat addPlatPatient(Plat pt, Integer idPatient) {
        pt.setDatePlat(LocalDate.now());

        Patient patient=patientREpository.findById(idPatient).get();
        LocalDate l =LocalDate.now();
        boolean tst=testMaladie(pt,idPatient);
        if (tst){
        float nbCalories=0;
        float somme=0.0F;
        List<Ingredient> ingredients = pt.getIngredients();
         for (Ingredient ing :ingredients){
             somme+=ing.getCalorie();
         }
        // nbCalories= ingredientRepository.sumCalorieByPlatsRepasPatientIdAndPlatsDatePlat(idPatient, l  );
        nbCalories +=platRepository.calculateCaloriesConsumedByUserToday(idPatient);

        log.info("nombre de calorie aujourd'hui"+nbCalories);
        float nbc=somme+nbCalories;

        //-----------------------------------Calcule nbCalorie estimé--------------------------------
        float longeur =patient.getLongueur();
        float poid = patient.getPoid();
        float nbCalorieEstimee=0.0F;
        LocalDate currentDate = LocalDate.now();
         int age = patientREpository.calculatePatientAgeById(idPatient);
        if(patient.getSexe().equals(Sexe.HOMME)){
            ////
            nbCalorieEstimee= (float) ((10*poid)+(6.25*longeur)-(5*age)+5);
        }

        if(patient.getSexe().equals(Sexe.FEMME)){
            nbCalorieEstimee= (float) ((10*poid)+(6.25*longeur)-(5*age)-161);
        }
        log.info("nombre de calorie"+nbc+"//////nombre de calorie estimé:"+nbCalorieEstimee);
        if(somme <= (nbCalorieEstimee/3)) {
            if (nbc < nbCalorieEstimee) {
                pt.setDatePlat(LocalDate.now());
                return platRepository.save(pt);
            } else {
                log.info("tu posséde ton nbre de calories");
            }
        }else{
            log.info("Vous avez dépassé les calories allouéees pour votre repas");
        }
        }else {
            log.info("tu peux pas ajouter ce plat");
        }
        return pt ;

    }

    @Override
    public List<Plat> getAll() {
        return platRepository.findAll();
    }

    @Override
    public void remove(int idf) {
        platRepository.deleteById(idf);
    }

    @Override
    public Plat update(Plat res) {
        res.setDatePlat(res.getDatePlat());
        return platRepository.save(res);
    }

    @Override
    public Boolean testMaladie(Plat plat, Integer idPatient) {
        Patient patient = patientREpository.findById(idPatient).orElse(null);

        if (patient == null) {
            log.error("Patient not found with ID: " + idPatient);
            return false;
        }

        List<Maladie> maladiesPatient = patient.getMaladies();
        List<Ingredient> ingredients = plat.getIngredients();
        List<Maladie> maladiesIngredients = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            maladiesIngredients.addAll(ingredient.getMaladies());
        }

        for (Maladie maladiePatient : maladiesPatient) {
            boolean maladieFound = false;

            for (Maladie maladieIngredient : maladiesIngredients) {
                if (maladieIngredient.getNom().equals(maladiePatient.getNom())) {
                    maladieFound = true;
                    break;
                }
            }

            if (!maladieFound) {
                log.info("Le patient et les ingrédients ne comportent pas la même maladie : " + maladiePatient.getNom());
                return false;
            }
        }

        return true;
    }





}
