package com.oura.backend.entity;

import com.oura.backend.id.HeartMetricsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "heart", catalog = "Metrics")
@IdClass(HeartMetricsId.class)
public class HeartMetricsEntity {
    // ToDo: Should @GeneratedValue be used here?
    @Id
    @Column(name = "id", unique = true, length = 36, nullable = false)
    private String id;

    @Column(name = "date", unique = true, nullable = false)
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
