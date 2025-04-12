package com.example.job_application.Job.Finder.Application.Public;


import com.example.job_application.Job.Finder.Application.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {


    @GetMapping("/health-check")
    public ResponseEntity<ApiResponse<Object>> getHealthCheck(){

        return ResponseEntity.ok(new ApiResponse<>(true,"Working fine!",null));
    }
}
