package com.oura.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class HeartMetricUpdate {
    String id;
    String date;
    double averageRestingHeartRate;
    double lowestRestingHeartRate;
    int averageHrv;
    int bloodPressure;
}
