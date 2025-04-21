package com.example.jobFinderApplication.service.external;

import com.example.jobFinderApplication.api.response.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
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

    private  final RedisService redisService;

    public WeatherService(RestTemplate restTemplate,RedisService redisService) {
        this.restTemplate = restTemplate;
        this.redisService = redisService;

    }

    public WeatherResponse getWeatherData(String location) {


        WeatherResponse weatherResponse =redisService.get("weather_of_"+location, WeatherResponse.class);

        if(weatherResponse !=null){
            return weatherResponse;
        }
            String url = String.format("%s/current?access_key=%s&query=%s", apiUrl, apiKey, location);
                try {
            WeatherResponse weatherRes= restTemplate.getForObject(url, WeatherResponse.class);
            if(weatherRes!=null){
                redisService.set("weather_of_"+location,weatherRes, 300L);
            }
            return  weatherRes;

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
