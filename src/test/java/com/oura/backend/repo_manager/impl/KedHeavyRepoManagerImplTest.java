package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.repo.IKedHeavyRepo;
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

    @Test
    public void getPagedKedHeavyReturnsResultsOfRepoCallWithNoData() {
        Page<KedHeavyEntity> expectedKedHeavyEntity = new PageImpl<>(Collections.emptyList());

        Page<KedHeavyEntity> mockPagedKedHeavyEntity = new PageImpl<>(Collections.emptyList());
        when(kedHeavyRepo.findAll(isA(PageRequest.class))).thenReturn(mockPagedKedHeavyEntity);

        Page<KedHeavyEntity> actualPagedKevHeavyEntity = subject.getPagedKedHeavy(0, 1);

        assertThat(actualPagedKevHeavyEntity.getTotalElements()).isEqualTo(0);
        assertThat(actualPagedKevHeavyEntity).isEqualTo(expectedKedHeavyEntity);

        verify(kedHeavyRepo, times(1)).findAll(isA(PageRequest.class));
        verifyNoMoreInteractions(kedHeavyRepo);
    }

    @Test
    public void getPagedKedHeavyReturnsResultsOfRepoCallWithData() {
        KedHeavyEntity expectedKedHeavyEntity = KedHeavyEntity.builder()
                .id("123")
                .build();
        Page<KedHeavyEntity> expectedPagedKedHeavyEntity =
                new PageImpl<>(Collections.singletonList(expectedKedHeavyEntity));

        KedHeavyEntity mockKedHeavyEntity = KedHeavyEntity.builder()
                .id("123")
                .build();
        Page<KedHeavyEntity> mockPagedKedHeavyEntity =
                new PageImpl<>(Collections.singletonList(mockKedHeavyEntity));

        when(kedHeavyRepo.findAll(isA(PageRequest.class))).thenReturn(mockPagedKedHeavyEntity);

        Page<KedHeavyEntity> actualPagedKedHeavyEntity = subject.getPagedKedHeavy(0, 1);

        assertThat(actualPagedKedHeavyEntity.getTotalElements()).isEqualTo(1);
        assertThat(actualPagedKedHeavyEntity).isEqualTo(expectedPagedKedHeavyEntity);

        verify(kedHeavyRepo, times(1)).findAll(isA(PageRequest.class));
        verifyNoMoreInteractions(kedHeavyRepo);
    }
}
