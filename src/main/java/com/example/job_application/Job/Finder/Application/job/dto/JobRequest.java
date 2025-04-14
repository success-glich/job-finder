package com.example.job_application.Job.Finder.Application.job.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;





@Data
public class JobRequest {
    private String title;
    private String description;
    private String location;
    private Double minSalary;
    private  Double maxSalary;



}
