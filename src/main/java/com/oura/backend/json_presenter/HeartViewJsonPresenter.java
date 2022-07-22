package com.oura.backend.json_presenter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oura.backend.entity.HeartMetricsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class HeartViewJsonPresenter {
    private String date;
    private double averageRestingHeartRate;
    private double lowestRestingHeartRate;
    private int averageHrv;
    private int bloodPressure;

    public static HeartViewJsonPresenter from(HeartMetricsEntity entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return HeartViewJsonPresenter.builder()
                .date(entity.getDate())
                .averageRestingHeartRate(entity.getAverageRestingHeartRate())
                .lowestRestingHeartRate(entity.getLowestRestingHeartRate())
                .averageHrv(entity.getAverageHrv())
                .bloodPressure(entity.getBloodPressure())
                .build();
    }

    public static  List<HeartViewJsonPresenter> from(List<HeartMetricsEntity> entities) throws IOException {
        List<HeartViewJsonPresenter> list = new ArrayList<>();

        for (HeartMetricsEntity entity: entities) {
            HeartViewJsonPresenter from = from(entity);
            list.add(from);
        }

        return list;
    }
}
