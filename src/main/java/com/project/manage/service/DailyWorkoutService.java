package com.project.manage.service;

import com.project.manage.Dto.ClientCompletionStatusDTO;
import com.project.manage.Dto.DailyWorkoutResponseDTO;
import com.project.manage.Dto.ExerciseDTO;
import com.project.manage.exception.WorkPlanNotFoundException;
import com.project.manage.model.*;
import com.project.manage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyWorkoutService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private DailyWorkoutRepository dailyWorkoutRepository;

    @Autowired
    private WorkoutPlanRepo workoutPlanRepo;

    @Autowired
    private WorkoutCompletionRepo workoutCompletionRepo;

    @Autowired
    private ExerciseRepo exerciseRepo;

    public  DailyWorkoutResponseDTO getTodayWorkout (String username , long planId){
        Optional<User> client= userRepository.findByName(username);
        if(client.isEmpty()){
           throw new RuntimeException("User not found");
        }
        Optional<WorkoutPlan> plan=workoutPlanRepo.findById(planId);
        WorkoutPlan workoutplan=plan.get();
        User user =client.get();
        Optional<Enrollment> enrollment=enrollmentRepo.findByClientAndWorkoutPlan(user,workoutplan);
        if (enrollment.isEmpty()) {
            throw new RuntimeException("User is not enrolled in this workout plan");
        }
        Enrollment enrol=enrollment.get();
        long days= ChronoUnit.DAYS.between(enrol.getEnrollmentDate() , LocalDate.now());
        int currentDay = (int) days+1;

        DailyWorkout dailyWorkout=dailyWorkoutRepository.findByWorkoutPlanAndDayNumber(workoutplan,currentDay);

        if (dailyWorkout == null) {
            throw new RuntimeException("No workout scheduled for today");
        }

        List<ExerciseDTO> exerciseDtoList=dailyWorkout.getExerciseList().stream().
                map(ex -> new ExerciseDTO(ex.getExcerciseId(),ex.getName()))
                .collect(Collectors.toList());

        return new DailyWorkoutResponseDTO(currentDay , exerciseDtoList);
    }

    public void makeComplete(String username , long planId , int dayNumber ,List<Long> exerciseIds){
            Optional<User> user=userRepository.findByName(username);
            if(user.isEmpty()){
                throw new UsernameNotFoundException("No user found to mark the exercise as completed");
            }
            User user1=user.get();

            Optional<WorkoutPlan> plan=workoutPlanRepo.findById(planId);
            if(plan.isEmpty()){
                throw new WorkPlanNotFoundException("No work plan found to mark as complete");
            }
            WorkoutPlan workoutPlan=plan.get();
            Optional<Enrollment> enrollment=enrollmentRepo.findByClientAndWorkoutPlan(user1,workoutPlan);
            if(enrollment.isEmpty()){
                throw new RuntimeException("WorkOut plan not exists to mark as complete");
            }
            Enrollment enrollment1=enrollment.get();
        DailyWorkout workout=dailyWorkoutRepository.findByWorkoutPlanAndDayNumber(workoutPlan,dayNumber);

        List<Exercise> validExercises = workout.getExerciseList();

        for (Long exerciseId : exerciseIds) {
            Exercise exercise = exerciseRepo.findById(exerciseId)
                    .orElseThrow(() -> new RuntimeException("Exercise not found: ID = " + exerciseId));

            if (!validExercises.contains(exercise)) {
                throw new IllegalArgumentException("Exercise ID " + exerciseId + " does not belong to Day " + dayNumber);
            }


            WorkoutCompletion completion = new WorkoutCompletion();
            completion.setCompleted(true);
            completion.setDate(LocalDate.now());
            completion.setEnrollment(enrollment1);
            completion.setDailyWorkout(workout);
            completion.setExercise(exercise);
            workoutCompletionRepo.save(completion);
        }
    }

    public ClientCompletionStatusDTO getClientCompletionStatus(String clientUsername, long planId, int dayNumber) {
        User client = userRepository.findByName(clientUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found"));

        WorkoutPlan workoutPlan = workoutPlanRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        Enrollment enrollment = enrollmentRepo.findByClientAndWorkoutPlan(client, workoutPlan)
                .orElseThrow(() -> new RuntimeException("Client not enrolled in the plan"));

        DailyWorkout workout = dailyWorkoutRepository.findByWorkoutPlanAndDayNumber(workoutPlan, dayNumber);
        if (workout == null) {
            throw new RuntimeException("No daily workout found for this day");
        }

        List<Exercise> exercises = workout.getExerciseList();
        int totalExercises = exercises.size();

        int completedExercises = 0;
        for (Exercise exercise : exercises) {
            boolean completed = workoutCompletionRepo.existsByEnrollmentAndDailyWorkoutAndExerciseAndDate(
                    enrollment, workout, exercise, LocalDate.now());
            if (completed) {
                completedExercises++;
            }
        }

        String status = (completedExercises == totalExercises) ? "COMPLETED" : "INCOMPLETE";

        return new ClientCompletionStatusDTO(
                clientUsername, planId, dayNumber, totalExercises, completedExercises, status
        );
    }

}
