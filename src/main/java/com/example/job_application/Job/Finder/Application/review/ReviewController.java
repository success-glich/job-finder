package com.example.job_application.Job.Finder.Application.review;


import com.example.job_application.Job.Finder.Application.review.dto.ReviewDto;
import com.example.job_application.Job.Finder.Application.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping("/reviews")
    public ResponseEntity<ApiResponse<List<Review>>> getAllReviewsByCompanyId(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviewsByCompanyId(companyId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Reviews fetched successfully.", reviews));
    }
    @PostMapping("/reviews")
    public ResponseEntity<ApiResponse<Review>> addReview(@PathVariable Long companyId,  @Validated  @RequestBody ReviewDto review) {
        Review addedReview = reviewService.addReview(companyId, review);
        return ResponseEntity.ok(new ApiResponse<>(true, "Review added successfully.", addedReview));
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<Review>> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Review fetched successfully.", review));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        reviewService.deleteReview(companyId, reviewId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Review deleted successfully.", null));
    }



}


//    @PutMapping("/reviews/{reviewId}")
//    public ResponseEntity<ApiResponse<Review>> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody ReviewDto review) {
//        Review updatedReview = reviewService.updateReview(companyId, reviewId, review);
//        return ResponseEntity.ok(new ApiResponse<>(true, "Review updated successfully.", updatedReview));
//    }