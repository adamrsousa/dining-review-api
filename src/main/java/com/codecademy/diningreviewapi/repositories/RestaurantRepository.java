package com.codecademy.diningreviewapi.repositories;

import com.codecademy.diningreviewapi.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    List<Restaurant> findByZipCodeOrderByZipCodeDesc(String zipCode);


}
