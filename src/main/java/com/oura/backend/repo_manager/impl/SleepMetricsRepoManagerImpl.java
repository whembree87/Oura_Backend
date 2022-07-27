package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.SleepMetricsEntity;
import com.oura.backend.repo.ISleepMetricsRepo;
import com.oura.backend.repo_manager.ISleepMetricsRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepMetricsRepoManagerImpl implements ISleepMetricsRepoManager {
    @Autowired
    private ISleepMetricsRepo sleepMetricsRepo;

    @Override
    public List<SleepMetricsEntity> getSleepMetrics() {
        return sleepMetricsRepo.findAll();
    }
}
