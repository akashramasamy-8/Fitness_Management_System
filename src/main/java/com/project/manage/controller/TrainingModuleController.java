package com.project.manage.controller;

import com.project.manage.model.WorkoutModule;
import com.project.manage.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainingModuleController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/workout-plans/{planId}/modules")
    public ResponseEntity<String> addModule(@PathVariable Long planId,
                                            @RequestBody WorkoutModule module,
                                            Authentication authentication) {
        String trainerName = authentication.getName();
        trainerService.addModuleToPlan(trainerName, planId, module);
        return ResponseEntity.ok("Module added successfully");
    }

    @PutMapping("/modules/{moduleId}")
    public ResponseEntity<String> updateModule(@PathVariable Long moduleId,
                                               @RequestBody WorkoutModule module,
                                               Authentication authentication) {
        String trainerName = authentication.getName();
        trainerService.updateModule(trainerName, moduleId, module);
        return ResponseEntity.ok("Module updated successfully");
    }

    @DeleteMapping("/modules/{moduleId}")
    public ResponseEntity<String> deleteModule(@PathVariable Long moduleId,
                                               Authentication authentication) {
        String trainerName = authentication.getName();
        trainerService.deleteModule(trainerName, moduleId);
        return ResponseEntity.ok("Module deleted successfully");
    }



}
