package com.oura.backend.controller;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.exceptions.HttpException;
import com.oura.backend.json_presenter.HeartViewJsonPresenter;
import com.oura.backend.repo_manager.IHeartMetricsRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class MetricViewController {
    @Autowired
    IHeartMetricsRepoManager heartMetricsRepoManager;

    @GetMapping(value="/getheartmetrics",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<HeartViewJsonPresenter> getHeartMetrics() {
        List<HeartMetricsEntity> entities = heartMetricsRepoManager.getHeartMetrics();

        try {
            if (!entities.isEmpty()) {
                return HeartViewJsonPresenter.from(entities);
            }

            throw new HttpException.HttpNoContentException();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new HttpException.HttpUnprocessableEntityException();
    }
}
