package com.oura.backend.json_presenter;

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
public class HeartMetricsJsonPresenter {
    private String id;
    private String date;
    private double averageRestingHeartRate;
    private double lowestRestingHeartRate;
    private int averageHrv;
    private String bloodPressure;

    public static HeartMetricsJsonPresenter from(HeartMetricsEntity entity) {
        return HeartMetricsJsonPresenter.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .averageRestingHeartRate(entity.getAverageRestingHeartRate())
                .lowestRestingHeartRate(entity.getLowestRestingHeartRate())
                .averageHrv(entity.getAverageHrv())
                .bloodPressure(entity.getBloodPressure())
                .build();
    }

    public static  List<HeartMetricsJsonPresenter> from(List<HeartMetricsEntity> entities) throws IOException {
        List<HeartMetricsJsonPresenter> list = new ArrayList<>();

        for (HeartMetricsEntity entity: entities) {
            HeartMetricsJsonPresenter from = from(entity);
            list.add(from);
        }

        return list;
    }
}
