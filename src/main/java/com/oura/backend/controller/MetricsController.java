package com.oura.backend.controller;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.entity.SleepMetricsEntity;
import com.oura.backend.exceptions.HttpException;
import com.oura.backend.json_presenter.HeartMetricsJsonPresenter;
import com.oura.backend.json_presenter.SleepMetricsJsonPresenter;
import com.oura.backend.model.HeartMetricBloodPressureUpdate;
import com.oura.backend.repo_manager.IHeartMetricsRepoManager;
import com.oura.backend.repo_manager.ISleepMetricsRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

        try {
            return HeartMetricsJsonPresenter.from(entities);
        } catch(IOException e) {
            e.printStackTrace();
        }

        throw new HttpException.HttpUnprocessableEntityException();
    }

    @GetMapping(value="/getpagedheartmetrics",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HeartMetricsJsonPresenter> getPagedHeartMetrics(@RequestParam int page, @RequestParam int size) {
        List<HeartMetricsEntity> entities = heartMetricsRepoManager.getPagedHeartMetrics(page, size).getContent();

        try {
            return HeartMetricsJsonPresenter.from(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new HttpException.HttpUnprocessableEntityException();
    }

    @GetMapping(value="/getsleepmetrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SleepMetricsJsonPresenter> getSleepMetrics() {
        List<SleepMetricsEntity> entities = sleepMetricsRepoManager.getSleepMetrics();

        return SleepMetricsJsonPresenter.from(entities);
    }

    @PostMapping(value = "/updatebloodpressure", produces = MediaType.APPLICATION_JSON_VALUE)
    public HeartMetricsJsonPresenter updateBloodPressure(@RequestBody HeartMetricBloodPressureUpdate bloodPressureUpdate) {
        HeartMetricsEntity updateBloodPressure;

        try {
            updateBloodPressure = heartMetricsRepoManager.updateHeartMetricBloodPressure(bloodPressureUpdate);
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException.HttpBadRequestException();
        }

        if (updateBloodPressure == null) {
            throw new HttpException.HttpNoContentException();
        }

        return HeartMetricsJsonPresenter.from(updateBloodPressure);
    }
}
