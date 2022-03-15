package com.codecademy.diningreviewapi.controllers;

import com.codecademy.diningreviewapi.enums.Status;
import com.codecademy.diningreviewapi.models.DiningReview;
import com.codecademy.diningreviewapi.models.Restaurant;
import com.codecademy.diningreviewapi.repositories.DiningReviewRepository;
import com.codecademy.diningreviewapi.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant/diningreview")
public class DiningReviewController {

    public final DiningReviewRepository diningRepo;
    public final RestaurantRepository restaurantRepo;

    public DiningReviewController (DiningReviewRepository diningRepo,
                                   RestaurantRepository restaurantRepo) {
        this.diningRepo=diningRepo;
        this.restaurantRepo=restaurantRepo;
    }

    @PostMapping("/")
    public DiningReview create(@RequestBody DiningReview review) {
        Optional<Restaurant> rest = restaurantRepo.findById(review.getRestaurant().getId());
            if(!rest.isPresent()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This restaurant does not exist");
            }
        DiningReview newReview= diningRepo.save(review);
        return newReview;
    }

    @GetMapping("/admin/listForApproval")
    public List<DiningReview> pendingApproval(){
        List<DiningReview> pendingReview = diningRepo.findByStatus(Status.PENDING);
        return pendingReview;
    }

    @PutMapping("/admin/{id}")
    public DiningReview changeStatus(@PathVariable Long id, @RequestBody DiningReview review) {
        Optional<DiningReview> change = diningRepo.findById(id);

        if(!change.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This review already exists");
        }

        DiningReview changeStatus = change.get();
        changeStatus.setStatus(review.getStatus());

        return changeStatus;
    }

    @GetMapping("{/{id}")
    public DiningReview findById(@PathVariable Long id) {
        Optional<DiningReview> diningReview= diningRepo.findById(id);
        if(!diningReview.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This review does not exist");
        }

        Optional<Restaurant> rest = restaurantRepo.findById(diningReview.get().getRestaurant().getId());
        if(!rest.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This restaurant does not exist");
        }

        DiningReview idRev = diningReview.get();
        return idRev;
    }

    @GetMapping("/")
    public List<DiningReview> getAllReviews(){
        List<DiningReview> reviews = diningRepo.findByStatus(Status.APPROVED);
        return reviews;
    }
}
