package com.oura.backend.repo_manager;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.repo.IHeartMetricsRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
