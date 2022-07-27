package com.oura.backend.repo_manager;

import com.oura.backend.entity.SleepMetricsEntity;

import java.util.List;

public interface ISleepMetricsRepoManager {
    List<SleepMetricsEntity> getSleepMetrics();
}
