package com.example.job_application.Job.Finder.Application.service.external;

import com.example.job_application.Job.Finder.Application.api.response.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class WeatherService {

    @Value("${weather.api.access-key}")
    private String apiKey;

    @Value("${weather.api.base-url}")
    private String apiUrl;

    private static final String  postUrl = "https://jsonplaceholder.typicode.com/posts";
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);


    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public WeatherResponse getWeatherData(String location) {

        String url = String.format("%s/current?access_key=%s&query=%s", apiUrl, apiKey, location);
        try {
            return restTemplate.getForObject(url, WeatherResponse.class);
        } catch (Exception e) {
            logger.error("Error fetching weather data: {}", e.getMessage());
            return  null;
        }
    }


//    * post request example
    public ResponseEntity<?> sendPost() {
        Map<String,Object> requestBody= new HashMap<>();

        requestBody.put("title","foo");
        requestBody.put("body", "bar");
        requestBody.put("userId", 1);

        try {
            ResponseEntity<?> response = restTemplate.postForEntity(postUrl, requestBody, Object.class);
            logger.info("Response from POST request: {}", response.getBody());
            return response;
        } catch (Exception e) {
            logger.error("Error sending POST request: {}", e.getMessage());
            return null;
        }
    }

}
