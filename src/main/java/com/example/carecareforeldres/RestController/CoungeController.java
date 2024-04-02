package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Counge;
import com.example.carecareforeldres.Repository.CoungeRepository;
import com.example.carecareforeldres.Service.CoungeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/counge")
@CrossOrigin("*")
public class CoungeController {
    CoungeService coungeService;
    CoungeRepository coungeRepository;

    @PostMapping("/add")
    public Counge ajouterCounge(@RequestBody Counge res){
        Counge p1=coungeService.add(res);
        return p1;
    }

    @GetMapping("/retrive_all_counge")
    public List<Counge> retrieveCoungeList(){

        return coungeService.getAll();
    }

    @GetMapping("/retrive_counge/{resId}")
    public Counge retrieveCounge(@PathVariable("resId") Integer resId){

        return coungeRepository.findById(resId).get();
    }

    @PutMapping("/update_counge")
    public Counge updateCounge(@RequestBody Counge restaurant){

        return coungeService.update(restaurant);
    }

    @DeleteMapping("/delete_counge/{coungeId}")
    public void deleteCounge(@PathVariable("coungeId") Integer coungeId){
        coungeService.remove(coungeId);
    }

    @PostMapping("/DemandeCoungeCuisine/{idCuisinier}")
    public Counge DemandeCoungeCuisine(@RequestBody Counge counge, @PathVariable("idCuisinier") Integer idCuisinier){
        return coungeService.DemandeCoungeCuisine(counge,idCuisinier);
    }
}
