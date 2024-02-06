package com.istasyon.backend.services;

import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleMapsService {
    @Value("${google.maps.apiKey}")
    private String apiKey;
    private final WebClient webClient;

    public GoogleMapsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://routes.googleapis.com").build();
    }

    public Map<String, Object> computeDistance(Employee employee, Company company) {
        String jsonRequestBody = "{"
                + "\"origin\":{"
                + "\"location\":{\"latLng\":{\"latitude\": " + employee.getxCoor() + ", \"longitude\": " + employee.getyCoor() + "}}"
                + "},"
                + "\"destination\":{"
                + "\"location\":{\"latLng\":{\"latitude\": " + company.getxCoor() + ", \"longitude\": " + company.getyCoor() + "}}"
                + "},"
                + "\"travelMode\": \"DRIVE\","
                + "\"routingPreference\": \"TRAFFIC_UNAWARE\","
                + "\"computeAlternativeRoutes\": false,"
                + "\"routeModifiers\": {"
                + "\"avoidTolls\": false,"
                + "\"avoidHighways\": false,"
                + "\"avoidFerries\": false"
                + "},"
                + "\"languageCode\": \"en-US\","
                + "\"units\": \"METRIC\""
                + "}";

        try {
            Mono<Map<String,Object>> responseMono = webClient.post()
                    .uri("/directions/v2:computeRoutes")
                    .header("Content-Type", "application/json")
                    .header("X-Goog-Api-Key", apiKey)
                    .header("X-Goog-FieldMask", "routes.duration,routes.distanceMeters,routes.polyline.encodedPolyline")
                    .bodyValue(jsonRequestBody)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
            return responseMono.block();
        } catch (WebClientResponseException e) {
            return new HashMap<String, Object>() {{
                put("Error", e.getResponseBodyAsString());
                put("StatusCode", e.getStatusCode());
            }};
        } catch (Exception e) {
            return new HashMap<String, Object>() {{
                put("Error", e.getMessage());
            }};
        }

    }
}
