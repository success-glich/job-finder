package com.example.jobFinderApplication.company.Impl;

import com.example.jobFinderApplication.company.Company;
import com.example.jobFinderApplication.company.CompanyRepo;
import com.example.jobFinderApplication.company.CompanyService;
import com.example.jobFinderApplication.company.dto.CompanyRequest;
import com.example.jobFinderApplication.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    private  static  final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        logger.info("Fetching all companies");
        return companyRepo.findAll();
    }

    @Override
    public Company createCompany(CompanyRequest companyRequest) {
        logger.info("Creating a new company with name: {}", companyRequest.getName());
        Company newCompany = new Company();
        newCompany.setName(companyRequest.getName());
        newCompany.setDescription(companyRequest.getDescription());
        logger.info("Company created successfully with ID: {}", newCompany.getId());
        return companyRepo.save(newCompany);
    }

    @Override
    public Company getCompanyById(Long id) {
        logger.info("Fetching company with ID :{}",id);
        return  companyRepo.findById(id).orElseThrow(()-> {
            logger.error("Company fetch byId:: Company not found with ID: {}", id);
            return new ResourceNotFoundException("Company not found with id: " + id);
        });

    }
    @Override
    public Company updateCompany(Long id, CompanyRequest companyRequest) {
        logger.info("Deleting updated with ID: {}", id);
        Company existingCompany = companyRepo.findById(id).orElseThrow(()->{
            logger.error("Company Update :: Company not found with ID: {}", id);
            return  new ResourceNotFoundException("Company not found with id: "+id);
        });
        existingCompany.setName(companyRequest.getName());
        existingCompany.setDescription(companyRequest.getDescription());
        logger.info("Company updated  successfully with ID: {}", id);
        return companyRepo.save(existingCompany);
    }


    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Company not found with id: "+id));
        companyRepo.delete(company);
    }


}
