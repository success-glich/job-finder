package com.example.job_application.Job.Finder.Application.Public;


import com.example.job_application.Job.Finder.Application.api.response.WeatherResponse;
import com.example.job_application.Job.Finder.Application.service.external.WeatherService;
import com.example.job_application.Job.Finder.Application.utility.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private  WeatherService weatherService;

//    public PublicController(WeatherService weatherService) {
//        this.weatherService = weatherService;
//    }

    @GetMapping("/health-check")
    public ResponseEntity<ApiResponse<Object>> getHealthCheck(){

        return ResponseEntity.ok(new ApiResponse<>(true,"Working fine!",null));
    }

    @GetMapping("/weather")
    public ResponseEntity<ApiResponse<String>> getWeatherDetailsByCity(@RequestParam(defaultValue = "Kathmandu") String city) {
        WeatherResponse weatherData = weatherService.getWeatherData(city);

        String responseMessage = "Weather data not available";

        if (weatherData != null && weatherData.getCurrent() != null) {
            int feelsLike = weatherData.getCurrent().getFeelsLike();
            log.info("Feels like temperature: {}", feelsLike);
            responseMessage = "Weather in " + city + " feels like " + feelsLike + "°C";
        }

        log.info("Weather data: {}", weatherData != null ? weatherData.getCurrent() : "No data");
        return ResponseEntity.ok(new ApiResponse<>(true, "Weather condition", responseMessage));
    }
}
