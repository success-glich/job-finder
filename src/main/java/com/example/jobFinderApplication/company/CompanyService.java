package com.example.jobFinderApplication.company;

import com.example.jobFinderApplication.company.dto.CompanyRequest;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company createCompany(CompanyRequest companyRequest);
    Company getCompanyById(Long id);
    Company updateCompany (Long id, CompanyRequest companyRequest);
    void deleteCompanyById(Long id);
}
