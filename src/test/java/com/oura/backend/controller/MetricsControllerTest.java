package com.oura.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.repo_manager.IHeartMetricsRepoManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class MetricsControllerTest {
    @Mock
    private IHeartMetricsRepoManager heartMetricsRepoManager;

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
        int bloodPressure = 0;

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

        assert (result.getResponse().getContentAsString())
                .equals(objectMapper.writeValueAsString(Collections.singletonList(expectedResult)));

        verify(heartMetricsRepoManager, times(1)).getHeartMetrics();
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }

    // ToDo: Complete me
    @Test
    public void getHeartMetricsReturns422IfUnableToPresentData() throws Exception {
        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(null)// Is this what makes the entity unprocessable?
                .build();

        when(heartMetricsRepoManager.getHeartMetrics()).thenReturn(Collections.singletonList(mockEntity));

        mockMvc.perform(get("/getheartmetrics")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity());

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
        int bloodPressure = 0;

        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id(id)
                .date(date)
                .averageRestingHeartRate(averageRestingHeartRate)
                .lowestRestingHeartRate(lowestRestingHeartRate)
                .averageHrv(averageHrv)
                .bloodPressure(bloodPressure)
                .build();
        String expectedJson = "[{\"id\":\"123\",\"date\":\"2022-07-30 00:00:00\",\"averageRestingHeartRate\":0.0,\"lowestRestingHeartRate\":0.0,\"averageHrv\":0,\"bloodPressure\":0}]";

        when(heartMetricsRepoManager.getHeartMetrics()).thenReturn(Collections.singletonList(mockEntity));

        MvcResult result = mockMvc.perform(get("/getheartmetrics")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assert (result.getResponse().getContentAsString()).equals(expectedJson);

        verify(heartMetricsRepoManager, times(1)).getHeartMetrics();
        verifyNoMoreInteractions(heartMetricsRepoManager);
    }
}
