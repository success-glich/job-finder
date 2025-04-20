package com.example.job_application.Job.Finder.Application.job.Impl;

import com.example.job_application.Job.Finder.Application.company.Company;
import com.example.job_application.Job.Finder.Application.company.Impl.CompanyServiceImpl;
import com.example.job_application.Job.Finder.Application.exception.ResourceNotFoundException;
import com.example.job_application.Job.Finder.Application.job.Job;
import com.example.job_application.Job.Finder.Application.job.JobRepo;
import com.example.job_application.Job.Finder.Application.job.JobService;
import com.example.job_application.Job.Finder.Application.job.dto.JobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {


    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CompanyServiceImpl companyService;

    @Override
    public List<Job> getAllJobs() {
        return  jobRepo.findAll();
    }

    @Override
    public Job createJob(JobRequest jobRequest) {

        Long companyId =jobRequest.getCompanyId();
        Company existingCompany = companyService.getCompanyById(companyId);
        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setMinSalary(jobRequest.getMinSalary());
        job.setMaxSalary(jobRequest.getMaxSalary());
        job.setCompany(existingCompany);
        return jobRepo.save(job);
    }
    //* return  companyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company not found with id: "+id));


    @Override
    @Cacheable(value="job", key="#id", unless="#result == null")
    public Job getJobById(Long id) {
        return jobRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found with id: "+id));
    }

    @Override
    @CachePut(value = "job", key="#id")
    public Job updateJobById(JobRequest jobRequest, Long id) {
        Job existingJob = jobRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found with id: "+id));
        existingJob.setTitle(jobRequest.getTitle());
        existingJob.setDescription(jobRequest.getDescription());
        existingJob.setMinSalary(jobRequest.getMinSalary());
        existingJob.setMaxSalary(jobRequest.getMaxSalary());
        return jobRepo.save(existingJob);
    }

    @Override
    @CacheEvict(value = "job", key="#id")
    public void deleteJobById(Long id) {
        Job existingJob = jobRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found with id: "+id));
        jobRepo.delete(existingJob);
    }

}
