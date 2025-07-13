package com.project.manage.service;

import com.project.manage.Dto.NutritionResponse;
import com.project.manage.exception.NutritionApiException;
import com.project.manage.model.NutritionLog;
import com.project.manage.model.User;
import com.project.manage.repository.NutritionLogRepo;
import com.project.manage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NutritionService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NutritionLogRepo nutritionLogRepo;

    @Autowired
    private UserRepository userRepository;

    private final String API_URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";
    private final String APP_ID = "4e9b5d0c";
    private final String APP_KEY = "56f9d0aa18cf0846717482db807b8eef";

    public NutritionResponse fetchAndStore(String ingredient, Long userId) {
        Optional<User> usr = userRepository.findById(userId);
        User user = usr.get();

        try {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-app-id", APP_ID);
            headers.set("x-app-key", APP_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Set body
            JSONObject body = new JSONObject();
            body.put("query", ingredient);

            HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

            // Make API call
            ResponseEntity<Map> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            // Parse the response
            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new NutritionApiException("Failed to fetch nutrition data from API.");
            }

            Map<String, Object> bodyMap = response.getBody();
            if (!bodyMap.containsKey("foods")) {
                throw new NutritionApiException("Invalid response format from Nutritionix API.");
            }

            List<Map<String, Object>> foods = (List<Map<String, Object>>) bodyMap.get("foods");

            double totalCalories = 0.0;
            double totalProtein = 0.0;
            double totalFat = 0.0;
            double totalCarbs = 0.0;

            for (Map<String, Object> food : foods) {
                totalCalories += ((Number) food.get("nf_calories")).doubleValue();
                totalProtein += ((Number) food.get("nf_protein")).doubleValue();
                totalFat += ((Number) food.get("nf_total_fat")).doubleValue();
                totalCarbs += ((Number) food.get("nf_total_carbohydrate")).doubleValue();
            }

            NutritionLog log = new NutritionLog();
            log.setUser(user);
            log.setIngredient(ingredient);
            log.setCalories(totalCalories);
            log.setProtein(totalProtein);
            log.setFat(totalFat);
            log.setCarbs(totalCarbs);
            log.setEntryDate(LocalDate.now());

            nutritionLogRepo.save(log);
            return new NutritionResponse(
                    ingredient,
                    totalCalories,
                    totalProtein,
                    totalFat,
                    totalCarbs
            );

        }catch (Exception e) {
            throw new NutritionApiException("Error calling Nutritionix API: " + e.getMessage());
        }
    }
}

