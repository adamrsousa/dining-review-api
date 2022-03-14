package com.codecademy.diningreviewapi.repositories;

import com.codecademy.diningreviewapi.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
