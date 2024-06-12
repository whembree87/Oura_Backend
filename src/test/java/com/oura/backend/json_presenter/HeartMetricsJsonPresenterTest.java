package com.oura.backend.json_presenter;

import com.oura.backend.entity.HeartMetricsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HeartMetricsJsonPresenterTest {

    @Test
    public void fromReturnsTheExpectedJsonPresentationFromTheGivenEntities() throws IOException {
        String id = "123";
        String date = "2022-07-29 00:00:00";
        double averageRestingHeartRate = 0.0;
        double lowestRestingHeartRate = 0.0;
        int averageHrv = 0;
        String bloodPressure = "0/0";

        HeartMetricsJsonPresenter expectedJsonPresentation =
                new HeartMetricsJsonPresenter(id, date, averageRestingHeartRate, lowestRestingHeartRate, averageHrv, bloodPressure);

        List<HeartMetricsJsonPresenter> expectedJsonListPresentation =
                Collections.singletonList(expectedJsonPresentation);

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();
        List<HeartMetricsEntity> mockEntities = Collections.singletonList(mockEntity);

        List<HeartMetricsJsonPresenter> actualJsonListPresentation = HeartMetricsJsonPresenter.from(mockEntities);

        assertThat(actualJsonListPresentation).isEqualTo(expectedJsonListPresentation);
    }
}
