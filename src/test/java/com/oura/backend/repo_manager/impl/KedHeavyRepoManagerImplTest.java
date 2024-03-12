package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.repo.IKedHeavyRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
}
