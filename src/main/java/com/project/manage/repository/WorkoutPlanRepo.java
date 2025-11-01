package com.project.manage.repository;

import com.project.manage.model.User;
import com.project.manage.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan , Long> {
    @Query("SELECT w.trainer FROM WorkoutPlan w WHERE w.workoutId = :workoutId")
    User findTrainerByWorkoutId(Long workoutId);
}
