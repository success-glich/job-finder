package com.example.job_application.Job.Finder.Application.company;

import com.example.job_application.Job.Finder.Application.company.dto.CompanyRequest;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company crateCompany(CompanyRequest companyRequest);
    Optional<Company> getCompanyById(Long id);
}
