package com.oura.backend.json_presenter;

import com.oura.backend.entity.SleepMetricsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SleepMetricsJsonPresenter {
    private String id;
    private String date;
    private int sleepScore;
    private int totalSleepScore;
    private int remSleepScore;
    private int deepSleepScore;
    private int sleepEfficiencyScore;
    private int restfulnessScore;
    private int sleepLatencyScore;
    private int sleepTimingScore;
    private int totalSleepDuration;
    private int totalBedtime;
    private int awakeTime;
    private int remSleepDuration;
    private int lightSleepDuration;
    private int deepSleepDuration;
    private int restlessSleep;
    private int sleepEfficiency;
    private int sleepLatency;
    private int sleepTiming;
    private String bedtimeStart;
    private String bedtimeEnd;

    public static SleepMetricsJsonPresenter from(SleepMetricsEntity entity) {
        return SleepMetricsJsonPresenter.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .sleepScore(entity.getSleepScore())
                .totalSleepScore(entity.getTotalSleepScore())
                .remSleepScore(entity.getRemSleepScore())
                .deepSleepScore(entity.getDeepSleepScore())
                .sleepEfficiencyScore(entity.getSleepEfficiencyScore())
                .restfulnessScore(entity.getRestfulnessScore())
                .sleepLatencyScore(entity.getSleepLatencyScore())
                .sleepTimingScore(entity.getSleepTimingScore())
                .totalSleepDuration(entity.getTotalSleepDuration())
                .totalBedtime(entity.getTotalBedtime())
                .awakeTime(entity.getAwakeTime())
                .remSleepDuration(entity.getRemSleepDuration())
                .lightSleepDuration(entity.getLightSleepDuration())
                .deepSleepDuration(entity.getDeepSleepDuration())
                .restlessSleep(entity.getRestlessSleep())
                .sleepEfficiency(entity.getSleepEfficiency())
                .sleepLatency(entity.getSleepLatency())
                .sleepTiming(entity.getSleepTiming())
                .bedtimeStart(entity.getBedtimeStart())
                .bedtimeEnd(entity.getBedtimeEnd())
                .build();
    }

    public static List<SleepMetricsJsonPresenter> from(List<SleepMetricsEntity> entities) {
        List<SleepMetricsJsonPresenter> list = new ArrayList<>();

        for (SleepMetricsEntity entity: entities) {
            SleepMetricsJsonPresenter from = from(entity);
            list.add(from);
        }

        return list;
    }
}
