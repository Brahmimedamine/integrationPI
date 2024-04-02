package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Cuisinier;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import com.example.carecareforeldres.Service.CuisinierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cuisinier")
@CrossOrigin("*")
public class CuisinierController {

    CuisinierService cuisinierService;
    CuisinierRepository cuisinierRepository;

    @PostMapping("/add")
    public Cuisinier ajouterCuisinier(@RequestBody Cuisinier res){
        Cuisinier p1=cuisinierService.add(res);
        return p1;
    }

    @GetMapping("/retrive_all_cuisinier")
    public List<Cuisinier> retrieveCuisinierList(){

        return cuisinierService.getAll();
    }

    @GetMapping("/retrive_cuisinier/{resId}")
    public Cuisinier retrieveCuisinier(@PathVariable("resId") Integer resId){

        return cuisinierRepository.findById(resId).get();
    }

    @PutMapping("/update_cuisinier")
    public Cuisinier updateCuisinier(@RequestBody Cuisinier restaurant){

        return cuisinierService.update(restaurant);
    }

    @DeleteMapping("/delete_cuisinier/{cuisinierId}")
    public void deleteCuisinier(@PathVariable("cuisinierId") Integer cuisinierId){
        cuisinierService.remove(cuisinierId);
    }

}
