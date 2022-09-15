package com.oura.backend.repo_manager;

import com.oura.backend.entity.HeartMetricsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IHeartMetricsRepoManager {
    List<HeartMetricsEntity> getHeartMetrics();

    Page<HeartMetricsEntity> getPagedHeartMetrics(int page, int size);
}
