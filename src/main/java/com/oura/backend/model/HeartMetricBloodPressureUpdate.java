package com.oura.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class HeartMetricBloodPressureUpdate {
    private String id;
    private String bloodPressure;
}
