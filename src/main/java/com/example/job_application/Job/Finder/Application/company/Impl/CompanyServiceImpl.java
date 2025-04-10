package com.example.job_application.Job.Finder.Application.company.Impl;

import com.example.job_application.Job.Finder.Application.company.Company;
import com.example.job_application.Job.Finder.Application.company.CompanyRepo;
import com.example.job_application.Job.Finder.Application.company.CompanyService;
import com.example.job_application.Job.Finder.Application.company.dto.CompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;


    @Override
    public List<Company> getAllCompanies() {

        return companyRepo.findAll();
    }

    @Override
    public Company crateCompany(CompanyRequest companyRequest) {
        Company newCompany = new Company();
        newCompany.setName(companyRequest.getName());
        newCompany.setDescription(companyRequest.getDescription());
        return companyRepo.save(newCompany);
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        Optional<Company> company = companyRepo.findById(id);
        return company;

    }


}
