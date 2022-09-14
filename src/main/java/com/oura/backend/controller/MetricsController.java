package com.oura.backend.controller;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.entity.SleepMetricsEntity;
import com.oura.backend.json_presenter.HeartMetricsJsonPresenter;
import com.oura.backend.json_presenter.SleepMetricsJsonPresenter;
import com.oura.backend.repo_manager.IHeartMetricsRepoManager;
import com.oura.backend.repo_manager.ISleepMetricsRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class MetricsController {
    @Autowired
    IHeartMetricsRepoManager heartMetricsRepoManager;
    @Autowired
    ISleepMetricsRepoManager sleepMetricsRepoManager;

    @GetMapping(value="/getheartmetrics",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HeartMetricsJsonPresenter> getHeartMetrics() {
        List<HeartMetricsEntity> entities = heartMetricsRepoManager.getHeartMetrics();

        return HeartMetricsJsonPresenter.from(entities);
    }

    @GetMapping(value="/getpagedheartmetrics",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HeartMetricsJsonPresenter> getPagedHeartMetrics() {
        List<HeartMetricsEntity> entities = heartMetricsRepoManager.getPagedHeartMetrics().getContent();

        return HeartMetricsJsonPresenter.from(entities);
    }

    @GetMapping(value="/getsleepmetrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SleepMetricsJsonPresenter> getSleepMetrics() {
        List<SleepMetricsEntity> entities = sleepMetricsRepoManager.getSleepMetrics();

        return SleepMetricsJsonPresenter.from(entities);
    }
}
