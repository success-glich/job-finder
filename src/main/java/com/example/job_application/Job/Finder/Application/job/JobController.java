package com.example.job_application.Job.Finder.Application.job;


import com.example.job_application.Job.Finder.Application.job.dto.JobRequest;
import com.example.job_application.Job.Finder.Application.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
   private JobService jobService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs(){
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(new ApiResponse<>(true,"All jobs fetched successfully.",jobs));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Job>> createJob(@RequestBody JobRequest jobRequest){
        Job job = jobService.createJob(jobRequest);
        return ResponseEntity.ok(new ApiResponse<>(true,"Job created successfully.",job));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Job fetched successfully.",job));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> updateJobById(@RequestBody JobRequest jobRequest, @PathVariable Long id){
        Job job = jobService.updateJobById(jobRequest,id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Job updated successfully.",job));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        jobService.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }

}
