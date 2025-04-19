package com.example.job_application.Job.Finder.Application.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findByCompanyId(Long companyId);

    Optional<Review> findByIdAndCompanyId(Long reviewId, Long companyId);
}
