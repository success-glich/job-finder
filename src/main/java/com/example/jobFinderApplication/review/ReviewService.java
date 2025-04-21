package com.example.jobFinderApplication.review;

import com.example.jobFinderApplication.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByCompanyId(Long companyId);
    Review addReview(Long companyId, ReviewDto review);
    Review getReviewById(Long companyId,Long reviewId);
    void deleteReview(Long companyId, Long reviewId);

}
