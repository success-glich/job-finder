package com.example.job_application.Job.Finder.Application.job;

import com.example.job_application.Job.Finder.Application.company.Company;
import com.example.job_application.Job.Finder.Application.entity.Auditable;
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

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "jobs")
public class Job extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Description should not be not null")
    private String description;

    @NotNull
    @Column(name = "min_salary")
    private Double minSalary;

    @NotNull
    @Column(name = "max_salary")
    private Double maxSalary;

//    @ManyToOne(fetch = FetchType.LAZY)

    @ManyToOne
    @JoinColumn(name="company_id", nullable = false)
    private Company company;




}