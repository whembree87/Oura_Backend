package com.oura.backend.repo;

import com.oura.backend.entity.HeartMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHeartMetricsRepo extends JpaRepository<HeartMetricsEntity, Integer> {
    List<HeartMetricsEntity> findAll();
}
