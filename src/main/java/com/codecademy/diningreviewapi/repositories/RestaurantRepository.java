package com.codecademy.diningreviewapi.repositories;

import com.codecademy.diningreviewapi.models.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    @Query(value = "SELECT r FROM restaurant WHERE zipCode = ?1 AND (peanutScore IS NOT NULL OR eggScore IS NOT NULL OR diaryScore IS NOT NULL) ORDER BY zip DESC")
    List<Restaurant> findByZipCodeWithAllergy(String zipCode);

}
