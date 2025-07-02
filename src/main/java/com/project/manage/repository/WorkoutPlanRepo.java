package com.project.manage.repository;

import com.project.manage.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan , Long> {

}
