package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.HeartMetricsEntity;
import com.oura.backend.repo.IHeartMetricsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
