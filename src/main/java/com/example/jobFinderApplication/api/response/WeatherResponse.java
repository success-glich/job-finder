package com.example.jobFinderApplication.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    public Current current;

    @Getter
    @Setter
    public static class Current { // Changed from private to public and added static
        @JsonProperty("observation_time")
        public String observationTime;
        public int temperature;

        @JsonProperty("feelslike")
        public int feelsLike;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("uv_index")
        public int uvIndex;

        public int visibility;
    }
}