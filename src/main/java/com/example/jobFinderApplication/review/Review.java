package com.example.jobFinderApplication.review;

import com.example.jobFinderApplication.company.Company;
import com.example.jobFinderApplication.entity.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "reviews")
@Data
public class Review  extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="description",nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(name="rating",nullable = false)
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating =0.0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="company_id",nullable = false)
    private Company company;

}
