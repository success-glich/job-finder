package com.example.jobFinderApplication.review.Impl;

import com.example.jobFinderApplication.company.Company;
import com.example.jobFinderApplication.company.CompanyService;
import com.example.jobFinderApplication.exception.ResourceNotFoundException;
import com.example.jobFinderApplication.review.Review;
import com.example.jobFinderApplication.review.ReviewRepo;
import com.example.jobFinderApplication.review.ReviewService;
import com.example.jobFinderApplication.review.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final CompanyService companyService;

    public ReviewServiceImp(ReviewRepo reviewRepo, CompanyService companyService) {
        this.reviewRepo = reviewRepo;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public Review addReview(Long companyId, ReviewDto review) {
        Company company = companyService.getCompanyById(companyId);
        Review newReview = new Review();
        newReview.setTitle(review.getTitle());
        newReview.setDescription(review.getDescription());

        newReview.setRating(review.getRating());
        newReview.setCompany(company);

        reviewRepo.save(newReview);

        return newReview;

    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {

        return reviewRepo.findByIdAndCompanyId(reviewId, companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + reviewId + " for company id " + companyId));


    }

    @Override
    public void deleteReview(Long companyId, Long reviewId) {

        Review review = reviewRepo.findByIdAndCompanyId(reviewId, companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + reviewId + " for company id " + companyId));

        reviewRepo.delete(review);

    }


}
