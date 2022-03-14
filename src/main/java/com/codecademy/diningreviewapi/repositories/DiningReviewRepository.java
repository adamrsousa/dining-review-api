package com.codecademy.diningreviewapi.repositories;

import com.codecademy.diningreviewapi.models.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {

    List<DiningReview> findByStatus(Enum status);


}
