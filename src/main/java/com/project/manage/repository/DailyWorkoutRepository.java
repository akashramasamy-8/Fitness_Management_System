package com.project.manage.repository;

import com.project.manage.model.DailyWorkout;
import com.project.manage.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyWorkoutRepository extends JpaRepository<DailyWorkout, Long> {

    DailyWorkout findByWorkoutPlanAndDayNumber(WorkoutPlan plan, int currentDay);
}
