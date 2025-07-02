package com.project.manage.repository;

import com.project.manage.model.WorkoutModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutModuleRepo extends JpaRepository<WorkoutModule,Long> {
}
