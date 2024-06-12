package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.model.HeartMetricBloodPressureUpdate;
import com.oura.backend.repo.IHeartMetricsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeartMetricsRepoManagerImplTest {
    @Mock
    private IHeartMetricsRepo heartMetricsRepo;

    @InjectMocks
    private HeartMetricsRepoManagerImpl subject;

    @Test
    public void getHeartMetricsReturnsResultsOfRepoCallWithNoData() {
        when(heartMetricsRepo.findAll()).thenReturn(Collections.emptyList());

        List<HeartMetricsEntity> result = subject.getHeartMetrics();

        assert result.isEmpty();

        verify(heartMetricsRepo, times(1)).findAll();
        verifyNoMoreInteractions(heartMetricsRepo);
    }

    @Test
    public void getHeartMetricsReturnsResultsOfRepoCallWithData() {
        HeartMetricsEntity mockEntity = HeartMetricsEntity.builder()
                .id("123")
                .build();
        List<HeartMetricsEntity> mockEntities = new ArrayList<>();
        mockEntities.add(mockEntity);

        List<HeartMetricsEntity> expectedEntities = new ArrayList<>();
        expectedEntities.add(mockEntity);

        when(heartMetricsRepo.findAll()).thenReturn(mockEntities);

        List<HeartMetricsEntity> result = subject.getHeartMetrics();

        assert result.size() == 1;
        assert result.equals(expectedEntities);

        verify(heartMetricsRepo, times(1)).findAll();
        verifyNoMoreInteractions(heartMetricsRepo);
    }

    @Test
    public void getPagedHeartMetricsReturnsResultsOfRepoCallWithNoData() {
        Page<HeartMetricsEntity> expectedPagedHeartMetricsEntity = new PageImpl<>(Collections.emptyList());

        Page<HeartMetricsEntity> mockPagedHeartMetricsEntity = new PageImpl<>(Collections.emptyList());
        when(heartMetricsRepo.findAll(isA(PageRequest.class))).thenReturn(mockPagedHeartMetricsEntity);

        Page<HeartMetricsEntity> actualPagedHeartMetricsEntity = subject.getPagedHeartMetrics(0, 1);

        assertThat(actualPagedHeartMetricsEntity.getTotalElements()).isEqualTo(0);
        assertThat(actualPagedHeartMetricsEntity).isEqualTo(expectedPagedHeartMetricsEntity);

        verify(heartMetricsRepo, times(1)).findAll(isA(PageRequest.class));
        verifyNoMoreInteractions(heartMetricsRepo);
    }

    @Test
    public void getPagedHeartMetricsReturnsResultsOfRepoCallWithData() {
        HeartMetricsEntity expectedHeartMetricsEntity = HeartMetricsEntity.builder()
                .id("123")
                .build();
        Page<HeartMetricsEntity> expectedPagedHeartMetricsEntity =
                new PageImpl<>(Collections.singletonList(expectedHeartMetricsEntity));

        HeartMetricsEntity mockHeartMetricsEntity = HeartMetricsEntity.builder()
                .id("123")
                .build();
        Page<HeartMetricsEntity> mockPagedHeartMetricsEntity =
                new PageImpl<>(Collections.singletonList(mockHeartMetricsEntity));

        when(heartMetricsRepo.findAll(isA(PageRequest.class))).thenReturn(mockPagedHeartMetricsEntity);

        Page<HeartMetricsEntity> actualPagedHeartMetricsEntity = subject.getPagedHeartMetrics(0, 1);

        assertThat(actualPagedHeartMetricsEntity.getTotalElements()).isEqualTo(1);
        assertThat(actualPagedHeartMetricsEntity).isEqualTo(expectedPagedHeartMetricsEntity);

        verify(heartMetricsRepo, times(1)).findAll(isA(PageRequest.class));
        verifyNoMoreInteractions(heartMetricsRepo);
    }

    @Test
    public void updateHeartMetricBloodPressureReturnsResultsOfRepoCallWithNoData() {
        when(heartMetricsRepo.getReferenceById(anyString())).thenReturn(null);

        HeartMetricBloodPressureUpdate entityToSave = HeartMetricBloodPressureUpdate.builder()
                .id("123")
                .build();

        HeartMetricsEntity actualEntity = subject.updateHeartMetricBloodPressure(entityToSave);

        assertThat(actualEntity).isEqualTo(null);

        verify(heartMetricsRepo, times(1)).getReferenceById(anyString());
        verifyNoMoreInteractions(heartMetricsRepo);
    }
    @Test
    public void updateHeartMetricBloodPressureUpdatesExistingMetricInDatabase() {
        HeartMetricsEntity expectedEntity = HeartMetricsEntity.builder()
                .id("123")
                .bloodPressure("0/0")
                .build();

        HeartMetricsEntity oldEntity = HeartMetricsEntity.builder()
                .id("123")
                .bloodPressure("10/10")
                .build();
        HeartMetricsEntity newEntity = HeartMetricsEntity.builder()
                .id("123")
                .bloodPressure("0/0")
                .build();

        when(heartMetricsRepo.getReferenceById(anyString())).thenReturn(oldEntity);
        when(heartMetricsRepo.save(isA(HeartMetricsEntity.class))).thenReturn(newEntity);

        HeartMetricBloodPressureUpdate entityToSave = HeartMetricBloodPressureUpdate.builder()
                .id("123")
                .bloodPressure("0/0")
                .build();
        HeartMetricsEntity actualEntity = subject.updateHeartMetricBloodPressure(entityToSave);

        assertThat(actualEntity).isEqualTo(expectedEntity);

        verify(heartMetricsRepo, times(1)).getReferenceById(anyString());
        verify(heartMetricsRepo, times(1)).save(isA(HeartMetricsEntity.class));
        verifyNoMoreInteractions(heartMetricsRepo);
    }
}
