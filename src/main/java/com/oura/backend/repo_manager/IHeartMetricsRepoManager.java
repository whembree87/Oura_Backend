package com.oura.backend.repo_manager;

import com.oura.backend.entity.HeartMetricsEntity;

import java.util.List;

public interface IHeartMetricsRepoManager {
    List<HeartMetricsEntity> getHeartMetrics();
}
