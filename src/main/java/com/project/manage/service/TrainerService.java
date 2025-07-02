package com.project.manage.service;

import com.project.manage.Dto.WorkoutPlanDTO;
import com.project.manage.exception.ContentNotHavingException;
import com.project.manage.exception.ResourceNotFoundException;
import com.project.manage.exception.UnauthorizedAccessException;
import com.project.manage.model.User;
import com.project.manage.model.WorkoutModule;
import com.project.manage.model.WorkoutPlan;
import com.project.manage.repository.UserRepository;
import com.project.manage.repository.WorkoutModuleRepo;
import com.project.manage.repository.WorkoutPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutPlanRepo workoutPlanRepo;
    @Autowired
    private WorkoutModuleRepo workoutModuleRepo;

    public void addPlan(String username , WorkoutPlan workoutPlan){
        Optional<User> user=userRepository.findByName(username);
        User u=user.get();
        if (workoutPlan.getTitle() == null || workoutPlan.getTitle().isBlank()) {
            throw new IllegalArgumentException("Workout title cannot be empty");
        }

        if (workoutPlan.getDescription() == null || workoutPlan.getDescription().isBlank()) {
            throw new IllegalArgumentException("Workout description cannot be empty");
        }

        workoutPlan.setTrainer(u);
        workoutPlanRepo.save(workoutPlan);
    }

    public List<WorkoutPlanDTO> getPlan(String name){
        List<WorkoutPlanDTO> list=new ArrayList<>();
        Optional<User> user=userRepository.findByName(name);
        User u=user.get();
        List<WorkoutPlan> plans=u.getPlan();
        for(WorkoutPlan plan:plans){
            String tname=plan.getTrainer().getName();
            String title=plan.getTitle();
            String desc=plan.getDescription();
            WorkoutPlanDTO workoutPlanDTO=new WorkoutPlanDTO(tname,title,desc);
            list.add(workoutPlanDTO);
        }
        return list;
    }

    public void updatePlan(Long id, String name , WorkoutPlan updatedPlan){
        WorkoutPlan existingPlan= workoutPlanRepo.findById(id)
                .orElseThrow(()->new NoSuchElementException("Workout plan with id "+id+" not found"));

        if(!existingPlan.getTrainer().getName().equals(name)){
            throw new ContentNotHavingException("This workplan is not this trainer "+name);
        }
        if (updatedPlan.getTitle() == null || updatedPlan.getTitle().isBlank() ||
                updatedPlan.getDescription() == null || updatedPlan.getDescription().isBlank()) {
            throw new ContentNotHavingException("Title and description cannot be empty");
        }
        existingPlan.setDescription(updatedPlan.getDescription());
        existingPlan.setTitle(updatedPlan.getTitle());
        workoutPlanRepo.save(existingPlan);
    }

    public void addModuleToPlan(String trainerName, Long planId, WorkoutModule module) {
        WorkoutPlan plan = workoutPlanRepo.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout plan not found"));

        if (!plan.getTrainer().getName().equals(trainerName)) {
            throw new UnauthorizedAccessException("You do not own this workout plan");
        }

        module.setWorkoutPlan(plan);
        workoutModuleRepo.save(module);
    }


    public void updateModule(String trainerName, Long moduleId, WorkoutModule updatedModule) {
        WorkoutModule existingModule = workoutModuleRepo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        if (!existingModule.getWorkoutPlan().getTrainer().getName().equals(trainerName)) {
            throw new UnauthorizedAccessException("You do not own this module");
        }

        if (updatedModule.getName() == null || updatedModule.getName().isBlank()) {
            throw new IllegalArgumentException("Module name cannot be empty");
        }

        existingModule.setName(updatedModule.getName());
        workoutModuleRepo.save(existingModule);
    }

    public void deleteModule(String trainerName, Long moduleId) {
        WorkoutModule module = workoutModuleRepo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        if (!module.getWorkoutPlan().getTrainer().getName().equals(trainerName)) {
            throw new UnauthorizedAccessException("You do not own this module");
        }

        workoutModuleRepo.delete(module);
    }



}
