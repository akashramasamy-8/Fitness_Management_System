package com.project.manage.repository;

import com.project.manage.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise,Long> {
}
