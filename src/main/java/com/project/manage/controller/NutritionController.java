package com.project.manage.controller;

import com.project.manage.Dto.NutritionRequest;
import com.project.manage.Dto.NutritionResponse;
import com.project.manage.model.NutritionLog;
import com.project.manage.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @PostMapping("/log")
    public ResponseEntity<NutritionResponse> logNutrition(@RequestBody NutritionRequest request){
        NutritionResponse response=nutritionService.fetchAndStore(request.getIngredient(),request.getUserId());
        return ResponseEntity.ok(response);
    }
}
