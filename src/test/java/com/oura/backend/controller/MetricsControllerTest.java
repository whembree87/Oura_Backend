package com.oura.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.entity.SleepMetricsEntity;
import com.oura.backend.json_presenter.HeartMetricsJsonPresenter;
import com.oura.backend.model.HeartMetricBloodPressureUpdate;
import com.oura.backend.repo_manager.IHeartMetricsRepoManager;
import com.oura.backend.repo_manager.ISleepMetricsRepoManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class MetricsControllerTest {
    @Mock
    private IHeartMetricsRepoManager heartMetricsRepoManager;
    @Mock
    private ISleepMetricsRepoManager sleepMetricsRepoManager;

    @InjectMocks
    private MetricsController subject;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getHeartMetricsReturns200AndListOfEntitiesOnSuccess() throws Exception {
        String id = "123";
        String date = "2022-07-29 00:00:00";
        double averageRestingHeartRate = 0.0;
        double lowestRestingHeartRate = 0.0;
        int averageHrv = 0;
        String bloodPressure = "0/0";

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();

        HeartMetricsEntity expectedResult = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();

        when(heartMetricsRepoManager.getHeartMetrics()).thenReturn(Collections.singletonList(mockEntity));

        MvcResult result = mockMvc
                .perform((get("/getheartmetrics")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(Collections.singletonList(expectedResult)));

        verify(heartMetricsRepoManager, times(1)).getHeartMetrics();
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void getHeartMetricsReturnsTheExpectedJson() throws Exception {
        String id = "123";
        String date = "2022-07-30 00:00:00";
        double averageRestingHeartRate = 0.0;
        double lowestRestingHeartRate = 0.0;
        int averageHrv = 0;
        String bloodPressure = "0/0";

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();
        String expectedJson = "[{\"id\":\"123\",\"date\":\"2022-07-30 00:00:00\",\"averageRestingHeartRate\":0.0,\"lowestRestingHeartRate\":0.0,\"averageHrv\":0,\"bloodPressure\":\"0/0\"}]";

        when(heartMetricsRepoManager.getHeartMetrics()).thenReturn(Collections.singletonList(mockEntity));

        MvcResult result = mockMvc.perform(get("/getheartmetrics")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);

        verify(heartMetricsRepoManager, times(1)).getHeartMetrics();
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void getPagedHeartMetricsReturns200AndListOfEntitiesOnSuccess() throws Exception {
        String id = "123";
        String date = "2022-07-29 00:00:00";
        double averageRestingHeartRate = 0.0;
        double lowestRestingHeartRate = 0.0;
        int averageHrv = 0;
        String bloodPressure = "0/0";

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();
        Page<HeartMetricsEntity> MockPagedEntity = new PageImpl<>(Collections.singletonList(mockEntity));

        HeartMetricsEntity expectedResult = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();

        when(heartMetricsRepoManager.getPagedHeartMetrics(anyInt(), anyInt())).thenReturn(MockPagedEntity);

        MvcResult result = mockMvc
                .perform((get("/getpagedheartmetrics"))
                        .param("page", "0")
                        .param("size", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(Collections.singletonList(expectedResult)));

        verify(heartMetricsRepoManager, times(1)).getPagedHeartMetrics(0, 1);
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void getPagedHeartMetricsReturnsTheExpectedJson() throws Exception {
        String id = "123";
        String date = "2022-07-30 00:00:00";
        double averageRestingHeartRate = 0.0;
        double lowestRestingHeartRate = 0.0;
        int averageHrv = 0;
        String bloodPressure = "0/0";

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();
        Page<HeartMetricsEntity> mockPagedEntity = new PageImpl<>(Collections.singletonList(mockEntity));
        String expectedJson = "[{\"id\":\"123\",\"date\":\"2022-07-30 00:00:00\",\"averageRestingHeartRate\":0.0,\"lowestRestingHeartRate\":0.0,\"averageHrv\":0,\"bloodPressure\":\"0/0\"}]";

        when(heartMetricsRepoManager.getPagedHeartMetrics(anyInt(), anyInt()))
                .thenReturn(mockPagedEntity);

        MvcResult result = mockMvc
                .perform((get("/getpagedheartmetrics"))
                        .param("page", "0")
                        .param("size", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);

        verify(heartMetricsRepoManager, times(1)).getPagedHeartMetrics(0, 1);
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void getSleepMetricsReturns200AndListOfEntitiesOnSuccess() throws Exception {
        String id = "123";
        String date = "2022-07-30 00:00:00";
        int sleepScore = 0;
        int totalSleepScore = 0;
        int remSleepScore = 0;
        int deepSleepScore = 0;
        int sleepEfficiencyScore = 0;
        int restfulnessScore = 0;
        int sleepLatencyScore = 0;
        int sleepTimingScore = 0;
        int totalSleepDuration = 0;
        int totalBedtime = 0;
        int awakeTime = 0;
        int remSleepDuration = 0;
        int lightSleepDuration = 0;
        int deepSleepDuration = 0;
        int restlessSleep = 0;
        int sleepEfficiency = 0;
        int sleepLatency = 0;
        int sleepTiming = 0;
        String bedtimeStart = "2022-07-30 00:00:00";
        String bedtimeEnd = "2022-07-30 00:00:00";

        SleepMetricsEntity mockEntity = SleepMetricsEntity.builder()
                .id(id)
                .date(date)
                .sleepScore(sleepScore)
                .totalSleepScore(totalSleepScore)
                .remSleepScore(remSleepScore)
                .deepSleepScore(deepSleepScore)
                .sleepEfficiencyScore(sleepEfficiencyScore)
                .restfulnessScore(restfulnessScore)
                .sleepLatencyScore(sleepLatencyScore)
                .sleepTimingScore(sleepTimingScore)
                .totalSleepDuration(totalSleepDuration)
                .totalBedtime(totalBedtime)
                .awakeTime(awakeTime)
                .remSleepDuration(remSleepDuration)
                .lightSleepDuration(lightSleepDuration)
                .deepSleepDuration(deepSleepDuration)
                .restlessSleep(restlessSleep)
                .sleepEfficiency(sleepEfficiency)
                .sleepLatency(sleepLatency)
                .sleepTiming(sleepTiming)
                .bedtimeStart(bedtimeStart)
                .bedtimeEnd(bedtimeEnd)
                .build();

        SleepMetricsEntity expectedResult = SleepMetricsEntity.builder()
                .id(id)
                .date(date)
                .sleepScore(sleepScore)
                .totalSleepScore(totalSleepScore)
                .remSleepScore(remSleepScore)
                .deepSleepScore(deepSleepScore)
                .sleepEfficiencyScore(sleepEfficiencyScore)
                .restfulnessScore(restfulnessScore)
                .sleepLatencyScore(sleepLatencyScore)
                .sleepTimingScore(sleepTimingScore)
                .totalSleepDuration(totalSleepDuration)
                .totalBedtime(totalBedtime)
                .awakeTime(awakeTime)
                .remSleepDuration(remSleepDuration)
                .lightSleepDuration(lightSleepDuration)
                .deepSleepDuration(deepSleepDuration)
                .restlessSleep(restlessSleep)
                .sleepEfficiency(sleepEfficiency)
                .sleepLatency(sleepLatency)
                .sleepTiming(sleepTiming)
                .bedtimeStart(bedtimeStart)
                .bedtimeEnd(bedtimeEnd)
                .build();

        when(sleepMetricsRepoManager.getSleepMetrics()).thenReturn(Collections.singletonList(mockEntity));

        MvcResult result = mockMvc
                .perform((get("/getsleepmetrics")).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(Collections.singletonList(expectedResult)));

        verify(sleepMetricsRepoManager, times(1)).getSleepMetrics();
        verifyNoMoreInteractions(sleepMetricsRepoManager);
    }

    @Test
    public void getSleepMetricsReturnsTheExpectedJson() throws Exception {
        String id = "123";
        String date = "2022-07-30 00:00:00";
        int sleepScore = 0;
        int totalSleepScore = 0;
        int remSleepScore = 0;
        int deepSleepScore = 0;
        int sleepEfficiencyScore = 0;
        int restfulnessScore = 0;
        int sleepLatencyScore = 0;
        int sleepTimingScore = 0;
        int totalSleepDuration = 0;
        int totalBedtime = 0;
        int awakeTime = 0;
        int remSleepDuration = 0;
        int lightSleepDuration = 0;
        int deepSleepDuration = 0;
        int restlessSleep = 0;
        int sleepEfficiency = 0;
        int sleepLatency = 0;
        int sleepTiming = 0;
        String bedtimeStart = "2022-07-30 00:00:00";
        String bedtimeEnd = "2022-07-30 00:00:00";

        SleepMetricsEntity mockEntity = SleepMetricsEntity.builder()
                .id(id)
                .date(date)
                .sleepScore(sleepScore)
                .totalSleepScore(totalSleepScore)
                .remSleepScore(remSleepScore)
                .deepSleepScore(deepSleepScore)
                .sleepEfficiencyScore(sleepEfficiencyScore)
                .restfulnessScore(restfulnessScore)
                .sleepLatencyScore(sleepLatencyScore)
                .sleepTimingScore(sleepTimingScore)
                .totalSleepDuration(totalSleepDuration)
                .totalBedtime(totalBedtime)
                .awakeTime(awakeTime)
                .remSleepDuration(remSleepDuration)
                .lightSleepDuration(lightSleepDuration)
                .deepSleepDuration(deepSleepDuration)
                .restlessSleep(restlessSleep)
                .sleepEfficiency(sleepEfficiency)
                .sleepLatency(sleepLatency)
                .sleepTiming(sleepTiming)
                .bedtimeStart(bedtimeStart)
                .bedtimeEnd(bedtimeEnd)
                .build();
        String expectedJson = "[{\"id\":\"123\",\"date\":\"2022-07-30 00:00:00\",\"sleepScore\":0,\"totalSleepScore\":0,\"remSleepScore\":0,\"deepSleepScore\":0,\"sleepEfficiencyScore\":0,\"restfulnessScore\":0,\"sleepLatencyScore\":0,\"sleepTimingScore\":0,\"totalSleepDuration\":0,\"totalBedtime\":0,\"awakeTime\":0,\"remSleepDuration\":0,\"lightSleepDuration\":0,\"deepSleepDuration\":0,\"restlessSleep\":0,\"sleepEfficiency\":0,\"sleepLatency\":0,\"sleepTiming\":0,\"bedtimeStart\":\"2022-07-30 00:00:00\",\"bedtimeEnd\":\"2022-07-30 00:00:00\"}]";

        when(sleepMetricsRepoManager.getSleepMetrics()).thenReturn(Collections.singletonList(mockEntity));

        MvcResult result = mockMvc.perform(get("/getsleepmetrics")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String actual = result.getResponse().getContentAsString();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);

        verify(sleepMetricsRepoManager, times(1)).getSleepMetrics();
        verifyNoMoreInteractions(sleepMetricsRepoManager);
    }

    @Test
    public void updateBloodPressureDeserializesTheData() throws Exception {
        String id = "123";
        String bloodPressure = "0/0";

        HeartMetricBloodPressureUpdate bloodPressureUpdate = HeartMetricBloodPressureUpdate.builder()
                .id(id)
                .bloodPressure(bloodPressure)
                .build();

        HeartMetricBloodPressureUpdate expectedBloodPressureUpdate = bloodPressureUpdate.toBuilder().build();

        mockMvc.perform(post("/updatebloodpressure")
                .content(objectMapper.writeValueAsString(bloodPressureUpdate))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        verify(heartMetricsRepoManager, times(1)).updateHeartMetricBloodPressure(expectedBloodPressureUpdate);
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void updateBloodPressureReturns400IfUnableToProcessUserBloodPressureData() throws Exception {
        HeartMetricBloodPressureUpdate bloodPressureUpdate = HeartMetricBloodPressureUpdate.builder().build();

        when(heartMetricsRepoManager.updateHeartMetricBloodPressure(any())).thenThrow(new IOException());

        mockMvc.perform(post("/updatebloodpressure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bloodPressureUpdate))
        )
                .andExpect(status().isBadRequest());

        verify(heartMetricsRepoManager, times(1)).updateHeartMetricBloodPressure(any());
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void updateBloodPressureReturns204IfNoHeartMetricIsAvailable() throws Exception {
        HeartMetricBloodPressureUpdate bloodPressureUpdate = HeartMetricBloodPressureUpdate.builder().build();

        when(heartMetricsRepoManager.updateHeartMetricBloodPressure(any())).thenReturn(null);

        mockMvc.perform(post("/updatebloodpressure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bloodPressureUpdate))
        )
                .andExpect(status().isNoContent());

        verify(heartMetricsRepoManager, times(1)).updateHeartMetricBloodPressure(any());
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    @Test
    public void updateBloodPressureReturns200AndEntityOnSuccess() throws Exception {
        String id = "123";
        String date = "2022-07-29 00:00:00";
        double averageRestingHeartRate = 10.0;
        double lowestRestingHeartRate = 10.0;
        int averageHrv = 10;
        String bloodPressure = "100/200";

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();

        HeartMetricsJsonPresenter expectedResult = HeartMetricsJsonPresenter.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();

        HeartMetricBloodPressureUpdate bloodPressureUpdate = HeartMetricBloodPressureUpdate.builder()
                .id("123")
                .bloodPressure("100/200")
                .build();

        when(heartMetricsRepoManager.updateHeartMetricBloodPressure(any())).thenReturn(mockEntity);

        MvcResult result = mockMvc.perform(post("/updatebloodpressure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bloodPressureUpdate))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(expectedResult));

        verify(heartMetricsRepoManager, times(1)).updateHeartMetricBloodPressure(bloodPressureUpdate);
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }
}
