package com.project.manage.service;

import com.project.manage.Dto.EnrollmentDTO;
import com.project.manage.Dto.WorkoutPlanDTO;
import com.project.manage.exception.ResourceNotFoundException;
import com.project.manage.model.Enrollment;
import com.project.manage.model.User;
import com.project.manage.model.WorkoutPlan;
import com.project.manage.repository.EnrollmentRepo;
import com.project.manage.repository.UserRepository;
import com.project.manage.repository.WorkoutPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.project.manage.model.Progress.STARTED;

@Service
public class ClientService {
    @Autowired
    private WorkoutPlanRepo workoutPlanRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnrollmentRepo enrollmentRepo;

    public List<WorkoutPlanDTO> getAllPlans(){
        List<WorkoutPlan> list=workoutPlanRepo.findAll();
        List<WorkoutPlanDTO> planDTOList=new ArrayList<>();
        for(WorkoutPlan plan:list){
            String name=plan.getTrainer().getName();
            String title=plan.getTitle();
            String desc=plan.getDescription();
            WorkoutPlanDTO workoutPlanDTO=new WorkoutPlanDTO(name,title,desc);
            planDTOList.add(workoutPlanDTO);
        }
        return planDTOList;
    }

    public void enrollInPlan(String username, Long workoutPlanId) {
        User client = userRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        WorkoutPlan plan = workoutPlanRepo.findById(workoutPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout plan not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setClient(client);
        enrollment.setWorkoutPlan(plan);
        enrollment.setProgressStatus(STARTED);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        enrollmentRepo.save(enrollment);
    }

    public List<EnrollmentDTO> getEnrolledPlans(String username) {
        User client = userRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return client.getEnrollments().stream().map(enroll -> {
            WorkoutPlan plan = enroll.getWorkoutPlan();
            return new EnrollmentDTO(
                    enroll.getEnrollmentId(),
                    plan.getTitle(),
                    plan.getDescription(),
                    plan.getTrainer().getName(),
                    enroll.getProgressStatus().toString()
            );
        }).toList();
    }
}
