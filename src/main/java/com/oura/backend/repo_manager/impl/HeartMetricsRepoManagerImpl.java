package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.HeartMetricsEntity;
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
}
