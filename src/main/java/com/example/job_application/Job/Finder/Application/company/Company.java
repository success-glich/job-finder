package com.example.job_application.Job.Finder.Application.company;

import com.example.job_application.Job.Finder.Application.entity.Auditable;
import com.example.job_application.Job.Finder.Application.job.Job;
import com.example.job_application.Job.Finder.Application.review.Review;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "companies")
//@EntityListeners(AuditingEntityListener.class) // ✅ Required for auditing to work
public class Company extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Review> reviews = new ArrayList<>();

//    @CreatedDate
//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    // @OneToMany(mappedBy = "company")

}
