package com.project.manage.controller;

import com.project.manage.Dto.CompletionRequestDTO;
import com.project.manage.Dto.DailyWorkoutResponseDTO;
import com.project.manage.service.DailyWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class DailyWorkoutController {
    @Autowired
    private DailyWorkoutService dailyWorkoutService;

    @GetMapping("/current-day")
    public ResponseEntity<DailyWorkoutResponseDTO> getCurrentDayworkout(
            @RequestParam Long planId , Authentication authentication){
        String username=authentication.getName();
        DailyWorkoutResponseDTO response =dailyWorkoutService.getTodayWorkout(username , planId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mark-complete")
    public ResponseEntity<String>  markCompletedStatus(@RequestBody CompletionRequestDTO completionRequestDTO,Authentication authentication){
        String name=authentication.getName();
        dailyWorkoutService.makeComplete(name , completionRequestDTO.getPlanId(),completionRequestDTO.getDayNumber(),completionRequestDTO.getExerciseIds());
        return ResponseEntity.ok("Mark work as completed");
    }

}
