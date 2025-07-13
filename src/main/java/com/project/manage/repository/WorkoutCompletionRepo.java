package com.project.manage.repository;

import com.project.manage.model.DailyWorkout;
import com.project.manage.model.Enrollment;
import com.project.manage.model.Exercise;
import com.project.manage.model.WorkoutCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WorkoutCompletionRepo extends JpaRepository<WorkoutCompletion,Long> {
    boolean existsByEnrollmentAndDailyWorkoutAndExerciseAndDate(Enrollment enrollment, DailyWorkout workout, Exercise exercise, LocalDate now);
}
