package com.example.job_application.Job.Finder.Application.company.Impl;

import com.example.job_application.Job.Finder.Application.company.Company;
import com.example.job_application.Job.Finder.Application.company.CompanyRepo;
import com.example.job_application.Job.Finder.Application.company.CompanyService;
import com.example.job_application.Job.Finder.Application.company.dto.CompanyRequest;
import com.example.job_application.Job.Finder.Application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;


    @Override
    public List<Company> getAllCompanies() {

        return companyRepo.findAll();
    }

    @Override
    public Company createCompany(CompanyRequest companyRequest) {
        Company newCompany = new Company();
        newCompany.setName(companyRequest.getName());
        newCompany.setDescription(companyRequest.getDescription());
        return companyRepo.save(newCompany);
    }

    @Override
    public Company getCompanyById(Long id) {
        return  companyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company not found with id: "+id));

    }
    @Override
    public Company updateCompany(Long id, CompanyRequest companyRequest) {

        Company existingCompany = companyRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Company not found with id: "+id));
        existingCompany.setName(companyRequest.getName());
        existingCompany.setDescription(companyRequest.getDescription());

        return companyRepo.save(existingCompany);
    }


    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Company not found with id: "+id));
        companyRepo.delete(company);
    }


}
