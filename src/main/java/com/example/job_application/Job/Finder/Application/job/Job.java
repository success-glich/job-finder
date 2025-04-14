package com.example.job_application.Job.Finder.Application.job;


import com.example.job_application.Job.Finder.Application.company.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "jobs")
@EntityListeners(AuditingEntityListener.class) // ✅ Required for auditing to work
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description should not be not null")

    private String description;

    @NotNull
    @Column(name = "min_salary")
    private Double minSalary;

    @NotNull
    @Column(name = "max_salary")
    private  Double maxSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private  Company company;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private  LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    @PrePersist
//    protected void onCreate() {
//        LocalDateTime createdAt = LocalDateTime.now();
//        this.updatedAt = java.time.LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        this.updatedAt = java.time.LocalDateTime.now();
//    }

}