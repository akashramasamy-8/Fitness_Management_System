package com.project.manage.controller;

import com.project.manage.Dto.EnrollmentDTO;
import com.project.manage.Dto.WorkoutPlanDTO;
import com.project.manage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/workout-plans")
    public ResponseEntity<List<WorkoutPlanDTO>> getAllPlans(){
        List<WorkoutPlanDTO> list=clientService.getAllPlans();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/enrollments")
    public ResponseEntity<String> enroll(@RequestParam Long planId, Authentication auth) {
        String username = auth.getName();
        clientService.enrollInPlan(username, planId);
        return ResponseEntity.ok("Enrollment successful");
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollments(Authentication auth) {
        String username = auth.getName();
        List<EnrollmentDTO> list = clientService.getEnrolledPlans(username);
        return ResponseEntity.ok(list);
    }
}
