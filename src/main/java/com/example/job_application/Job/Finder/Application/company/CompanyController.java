package com.example.job_application.Job.Finder.Application.company;


import com.example.job_application.Job.Finder.Application.company.dto.CompanyRequest;
import com.example.job_application.Job.Finder.Application.utility.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> getAllCompanies() {
        List<Company> allCompanies = companyService.getAllCompanies();

        return ResponseEntity.ok(new ApiResponse<>(true,"All companies fetched successfully.",allCompanies));

    }


    @PostMapping
    public ResponseEntity<ApiResponse<Company>> createCompany(@Valid @RequestBody CompanyRequest companyRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    false,
                    "all fields are required",
                    null
            ));

        }

        Company company = companyService.crateCompany(companyRequest);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Company created successfully",
                company
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);

        return company.map(value -> ResponseEntity.ok(new ApiResponse<>(true, "Company fetched successfully.", value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Company not found", null)));

    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(){
        return ResponseEntity.ok("Route is working");

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteCompanyById(){
        return ResponseEntity.ok("Route is working");
    }


}


/*


// demo controller
 public ResponseEntity<ApiResponse<List<Company>>> getAllCompanies() {
        try {
            List<Company> companies = companyRepo.findAll();

            if (companies.isEmpty()) {
                ApiResponse<List<Company>> response = new ApiResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        "No companies found",
                        null
                );
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }

            ApiResponse<List<Company>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Companies fetched successfully",
                    companies
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponse<List<Company>> response = new ApiResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while fetching companies",
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
 */