package com.example.jobFinderApplication.job;

import com.example.jobFinderApplication.company.Company;
import com.example.jobFinderApplication.entity.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "jobs")
public class Job extends Auditable  implements Serializable {

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



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="company_id", nullable = false)
    private Company company;




}