package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Restaurant;
import com.example.carecareforeldres.Repository.RestaurantRepository;
import com.example.carecareforeldres.Service.IServiceRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {
    IServiceRestaurant iServiceRestaurant;
    RestaurantRepository restaurantRepository;

    @PostMapping("/add")
    public Restaurant ajouterRestaurant(@RequestBody Restaurant res){
        Restaurant p1=iServiceRestaurant.addRes(res);
        return p1;
    }

    @GetMapping("/retrive_all_restaurant")
    public List<Restaurant> retrieveFoodList(){

        return iServiceRestaurant.getAll();
    }

    @GetMapping("/retrive_restaurant/{resId}")
    public Restaurant retrieveFood(@PathVariable("resId") Integer resId){

        return restaurantRepository.findById(resId).get();
    }

    @PutMapping("/update_restaurant")
    public Restaurant updateFood(@RequestBody Restaurant restaurant){

        return iServiceRestaurant.update(restaurant);
    }

    @DeleteMapping("/delete_restaurant/{foodId}")
    public void deleteFood(@PathVariable("foodId") Integer foodId){
        iServiceRestaurant.remove(foodId);
    }

}
