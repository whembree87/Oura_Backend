package com.oura.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sleep")
public class SleepMetricsEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "date")
    private String date;

    @Column(name = "sleep_score")
    private int sleepScore;

    @Column(name = "total_sleep_score")
    private int totalSleepScore;

    @Column(name = "rem_sleep_score")
    private int remSleepScore;

    @Column(name = "deep_sleep_score")
    private int deepSleepScore;

    @Column(name = "sleep_efficiency_score")
    private int sleepEfficiencyScore;

    @Column(name = "restfulness_score")
    private int restfulnessScore;

    @Column(name = "sleep_latency_score")
    private int sleepLatencyScore;

    @Column(name = "sleep_timing_score")
    private int sleepTimingScore;

    @Column(name = "total_sleep_duration")
    private int totalSleepDuration;

    @Column(name = "total_bedtime")
    private int totalBedtime;

    @Column(name = "awake_time")
    private int awakeTime;

    @Column(name = "rem_sleep_duration")
    private int remSleepDuration;

    @Column(name = "light_sleep_duration")
    private int lightSleepDuration;

    @Column(name = "deep_sleep_duration")
    private int deepSleepDuration;

    @Column(name = "restless_sleep")
    private int restlessSleep;

    @Column(name = "sleep_efficiency")
    private int sleepEfficiency;

    @Column(name = "sleep_latency")
    private int sleepLatency;

    @Column(name = "sleep_timing")
    private int sleepTiming;

    @Column(name = "bedtime_start")
    private String bedtimeStart;

    @Column(name = "bedtime_end")
    private String bedtimeEnd;
}
