package com.example.job_application.Job.Finder.Application.job;

import com.example.job_application.Job.Finder.Application.job.dto.JobRequest;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    Job createJob(JobRequest jobRequest);
    Job getJobById(Long id);
    Job updateJobById(JobRequest jobRequest, Long id);
    void deleteJobById(Long id);
}
