package com.oura.backend.repo_manager;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.model.HeartMetricUpdate;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IHeartMetricsRepoManager {
    List<HeartMetricsEntity> getHeartMetrics();

    Page<HeartMetricsEntity> getPagedHeartMetrics(int page, int size);

    HeartMetricsEntity updateHeartMetric(HeartMetricUpdate heartMetricUpdate) throws IOException;
}
