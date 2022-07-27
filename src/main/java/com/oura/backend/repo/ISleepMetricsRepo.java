package com.oura.backend.repo;

import com.oura.backend.entity.SleepMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISleepMetricsRepo extends JpaRepository<SleepMetricsEntity, Integer> {
    List<SleepMetricsEntity> findAll();
}
