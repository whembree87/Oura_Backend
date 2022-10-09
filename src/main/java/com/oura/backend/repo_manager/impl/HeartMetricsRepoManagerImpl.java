package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.model.HeartMetricUpdate;
import com.oura.backend.repo.IHeartMetricsRepo;
import com.oura.backend.repo_manager.IHeartMetricsRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeartMetricsRepoManagerImpl implements IHeartMetricsRepoManager {
    @Autowired
    private IHeartMetricsRepo heartMetricsRepo;

    @Override
    public List<HeartMetricsEntity> getHeartMetrics() {
        return heartMetricsRepo.findAll();
    }

    @Override
    public Page<HeartMetricsEntity> getPagedHeartMetrics(int page, int size) {
        return heartMetricsRepo.findAll(PageRequest.of(page, size));
    }
    @Override
    public HeartMetricsEntity updateHeartMetric(HeartMetricUpdate heartMetricUpdate) {
        String id = heartMetricUpdate.getId();

        HeartMetricsEntity entityToUpdate = heartMetricsRepo.getReferenceById(id);

        if (entityToUpdate == null) {
            return null;
        }

        entityToUpdate.setId(heartMetricUpdate.getId());
        entityToUpdate.setDate(heartMetricUpdate.getDate());
        entityToUpdate.setAverageRestingHeartRate(heartMetricUpdate.getAverageRestingHeartRate());
        entityToUpdate.setLowestRestingHeartRate(heartMetricUpdate.getLowestRestingHeartRate());
        entityToUpdate.setAverageHrv(heartMetricUpdate.getAverageHrv());
        entityToUpdate.setBloodPressure(heartMetricUpdate.getBloodPressure());

        return heartMetricsRepo.save(entityToUpdate);
    }
}
