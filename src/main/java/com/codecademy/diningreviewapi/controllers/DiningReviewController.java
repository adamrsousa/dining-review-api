package com.codecademy.diningreviewapi.controllers;

import com.codecademy.diningreviewapi.enums.Status;
import com.codecademy.diningreviewapi.models.DiningReview;
import com.codecademy.diningreviewapi.repositories.DiningReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diningreview")
public class DiningReviewController {

    public final DiningReviewRepository diningRepo;

    public DiningReviewController (DiningReviewRepository diningRepo) {
        this.diningRepo=diningRepo;
    }

    @PostMapping("/")
    public DiningReview create(@RequestBody DiningReview review) {
        DiningReview newReview= diningRepo.save(review);
        return newReview;
    }

    @GetMapping("/listForApproval")
    public List<DiningReview> pendingApproval(){
        List<DiningReview> pendingReview = diningRepo.findByStatus(Status.PENDING);
        return pendingReview;
    }

    @PutMapping("/{id}")
    public DiningReview changeStatus(@PathVariable Long id, @RequestBody DiningReview review) {
        Optional<DiningReview> change = diningRepo.findById(id);

        if(!change.isPresent()) {
            return null;
        }

        DiningReview changeStatus = change.get();
        changeStatus.setStatus(review.getStatus());

        return changeStatus;
    }

    @GetMapping("/")
    public Iterable<DiningReview> getAllReviews(){
        Iterable<DiningReview> reviews = diningRepo.findAll();
        return reviews;
    }
}
