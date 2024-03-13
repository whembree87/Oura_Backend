package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.repo.IKedHeavyRepo;
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
public class KedHeavyRepoManagerImplTest {
    @Mock
    private IKedHeavyRepo kedHeavyRepo;

    @InjectMocks
    private KedHeavyRepoManagerImpl subject;

    @Test
    public void getKedHeavyReturnsResultsOfRepoCallWithNoData() {
        when(kedHeavyRepo.findAll()).thenReturn(Collections.emptyList());

        List<KedHeavyEntity> result = subject.getKedHeavy();

        assert result.isEmpty();

        verify(kedHeavyRepo, times(1)).findAll();
        verifyNoMoreInteractions(kedHeavyRepo);
    }

    @Test
    public void getKedHeavyReturnsResultsOfRepoCallWithData(){
        KedHeavyEntity mockEntity = KedHeavyEntity.builder()
                .id("123")
                .build();
        List<KedHeavyEntity> mockEntities = new ArrayList<>();
        mockEntities.add(mockEntity);

        List<KedHeavyEntity> expectedEntities = new ArrayList<>();
        expectedEntities.add(mockEntity);

        when(kedHeavyRepo.findAll()).thenReturn(mockEntities);

        List<KedHeavyEntity> result = subject.getKedHeavy();

        assert result.size() == 1;
        assert result.equals(expectedEntities);

        verify(kedHeavyRepo, times(1)).findAll();
        verifyNoMoreInteractions(kedHeavyRepo);
    }
}
