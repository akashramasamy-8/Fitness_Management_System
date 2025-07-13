package com.project.manage.controller;

import com.project.manage.Dto.ClientCompletionStatusDTO;
import com.project.manage.Dto.WorkoutPlanDTO;
import com.project.manage.jwt.JwtUtil;
import com.project.manage.model.WorkoutPlan;
import com.project.manage.service.DailyWorkoutService;
import com.project.manage.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer/workout-plans")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @Autowired
    private DailyWorkoutService dailyWorkoutService;

    @PostMapping
    public ResponseEntity<String> createPlan(@RequestBody WorkoutPlan workoutPlan , Authentication authentication){
        String name=authentication.getName();
        trainerService.addPlan(name,workoutPlan);
        return ResponseEntity.ok("WorkoutPlan added successfully");
    }
    @GetMapping
    public ResponseEntity<List<WorkoutPlanDTO>> getAllplan(Authentication authentication){
        String name=authentication.getName();
        return ResponseEntity.ok(trainerService.getPlan(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editPlan(@PathVariable Long id, @RequestBody WorkoutPlan workoutPlan,Authentication authentication){
        String name= authentication.getName();
        trainerService.updatePlan(id,name,workoutPlan);
        return ResponseEntity.ok("Updated successfully");
    }

    @GetMapping("/trainer/client-completion")
    public ResponseEntity<ClientCompletionStatusDTO> getClientCompletionStatus(
            @RequestParam String clientUsername,
            @RequestParam long planId,
            @RequestParam int dayNumber) {

        ClientCompletionStatusDTO statusDTO = dailyWorkoutService.getClientCompletionStatus(
                clientUsername, planId, dayNumber);

        return ResponseEntity.ok(statusDTO);
    }
}
