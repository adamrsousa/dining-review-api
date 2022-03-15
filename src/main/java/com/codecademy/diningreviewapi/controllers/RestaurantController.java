package com.codecademy.diningreviewapi.controllers;

import com.codecademy.diningreviewapi.models.Restaurant;
import com.codecademy.diningreviewapi.repositories.DiningReviewRepository;
import com.codecademy.diningreviewapi.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Restaurant already exists");
            }
        }

        Restaurant newRestaurant = restaurantRepo.save(restaurant);

        return newRestaurant;
    }

    @GetMapping("{/{id}")
    public Restaurant findById(@PathVariable Long id) {
        Optional<Restaurant> restaurant= restaurantRepo.findById(id);
        if(!restaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Restaurant does not exist");
        }
        Restaurant idRest = restaurant.get();
        return idRest;
    }

    @GetMapping("/")
    public List<Restaurant> findByZipCodeAndAllergy(@RequestBody String zipCode) {

        List<Restaurant> findZip= restaurantRepo.findByZipCodeWithAllergy(zipCode);

        return findZip;
    }

}

