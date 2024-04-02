package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Restaurant;
import com.example.carecareforeldres.Repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService implements IServiceRestaurant{

    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant addRes(Restaurant res) {return restaurantRepository.save(res);}
    @Override
    public List<Restaurant> getAll(){return restaurantRepository.findAll();}

    @Override
    public void remove(int idf) {
        restaurantRepository.deleteById(idf);}

    @Override
    public Restaurant update(Restaurant res) {
        return restaurantRepository.save(res);
    }
}
