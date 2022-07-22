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
@Table(name = "heart")
public class HeartMetricsEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "date")
    private String date;

    @Column(name = "average_resting_heart_rate")
    private double averageRestingHeartRate;

    @Column(name = "lowest_resting_heart_rate")
    private double lowestRestingHeartRate;

    @Column(name = "average_hrv")
    private int averageHrv;

    @Column(name = "blood_pressure")
    private int bloodPressure;
}
