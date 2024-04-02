package com.example.carecareforeldres.RestController;


import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Service.IShelterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/shelter")
public class ShelterController {
    IShelterService iShelterService;

    @GetMapping("/allShelter")
    public List<Shelter> getShelter() {
        List<Shelter> listShelters = iShelterService.retrieveAllShelter();
        return listShelters;
    }

    @GetMapping("/Shelter/{id}")
    public Shelter retrieveShelter(@PathVariable("id") Long id) {
        return iShelterService.retrieveShelter(id);
    }

    @PostMapping("/addShelter")
    public Shelter addEtudiant(@RequestBody Shelter b) {
        Shelter Shelter = iShelterService.addShelter(b);
        return Shelter;
    }

    @PutMapping("/UpdateShelter")
    public Shelter updateShelter(@RequestBody Shelter e) {
        Shelter Shelter = iShelterService.updateShelter(e);
        return Shelter;
    }

    @DeleteMapping("/RemoveShelter/{id}")
    public void removeShelter(@PathVariable("id") Long id) {
        iShelterService.removeShelter(id);
    }


    @PutMapping("/affect/{idAide}")
    @ResponseBody
    public Shelter assignAideToShelterrrr(@PathVariable("idAide") Long idAide, @RequestBody Shelter shelter) {
        return iShelterService.assignAideToShelter(idAide,shelter);
    }
}

