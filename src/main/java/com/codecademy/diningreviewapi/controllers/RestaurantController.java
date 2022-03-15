package com.codecademy.diningreviewapi.controllers;

import com.codecademy.diningreviewapi.models.Restaurant;
import com.codecademy.diningreviewapi.repositories.RestaurantRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    public final RestaurantRepository restaurantRepo;

    public RestaurantController(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @PostMapping("/")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepo.findAll();
        for(int i=0; i<restaurants.size(); i++) {
            Boolean condition = restaurant.getZipCode().equals(restaurants.get(i).getZipCode())
                    &&restaurant.getName().equals(restaurants.get(i).getName());
            if(condition){
                return null;
            }
        }

        Restaurant newRestaurant = restaurantRepo.save(restaurant);

        return newRestaurant;
    }

    @GetMapping("{/{id}")
    public Restaurant findById(@PathVariable Long id) {
        Optional<Restaurant> restaurant= restaurantRepo.findById(id);
        if(!restaurant.isPresent()) {
            return null;
        }
        Restaurant idRest = restaurant.get();
        return idRest;
    }

    @GetMapping("/")
    public List<Restaurant> findByZipCodeAndAllergy(@RequestBody String zipCode,
                                                    @RequestBody String allergy) {

    }

}

