package com.project.manage.repository;

import com.project.manage.model.NutritionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionLogRepo extends JpaRepository<NutritionLog, Long> {

}
