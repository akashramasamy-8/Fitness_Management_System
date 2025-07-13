package com.project.manage.repository;

import com.project.manage.model.Enrollment;
import com.project.manage.model.User;
import com.project.manage.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByClientAndWorkoutPlan(User user, WorkoutPlan plan);
}
