package com.example.job_application.Job.Finder.Application.company;

import com.example.job_application.Job.Finder.Application.utility.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="companies")
public class Company  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    private  String name;

    @NotNull
    private  String Description;

    @CreatedDate
    @Column(name = "created_at", updatable = false)

    private LocalDateTime createdAt=LocalDateTime.now();
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt=LocalDateTime.now();




}
