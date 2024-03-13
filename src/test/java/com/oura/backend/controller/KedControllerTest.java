package com.oura.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.repo_manager.IKedHeavyRepoManager;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class KedControllerTest {
    @Mock
    private IKedHeavyRepoManager kedHeavyRepoManager;

    @InjectMocks
    private KedController subject;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getKedHeavyReturns200AndListOfEntitiesOnSuccess() throws Exception {
        String id = "123";
        int week = 1;
        String date = "2022-07-29 00:00:00";
        int weight = 150;
        int set1 = 2;
        int set2 = 3;
        int set3 = 5;
        int set4 = 2;
        int set5 = 3;
        int set6 = 5;
        int set7 = 2;
        int set8 = 3;
        int set9 = 5;
        String notes = "Blah!";

        KedHeavyEntity mockEntity = KedHeavyEntity.builder()
                .id(id)
                .week(week)
                .date(date)
                .weight(weight)
                .set1(set1)
                .set2(set2)
                .set3(set3)
                .set4(set4)
                .set5(set5)
                .set6(set6)
                .set7(set7)
                .set8(set8)
                .set9(set9)
                .notes(notes)
                .build();

        KedHeavyEntity expectedResult = KedHeavyEntity.builder()
                .id(id)
                .week(week)
                .date(date)
                .weight(weight)
                .set1(set1)
                .set2(set2)
                .set3(set3)
                .set4(set4)
                .set5(set5)
                .set6(set6)
                .set7(set7)
                .set8(set8)
                .set9(set9)
                .notes(notes)
                .build();

        when(kedHeavyRepoManager.getKedHeavy()).thenReturn(Collections.singletonList(mockEntity));

        MvcResult result = mockMvc
                .perform((get("/getkedheavy")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(Collections.singletonList(expectedResult)));

        verify(kedHeavyRepoManager, times(1)).getKedHeavy();
        verifyNoMoreInteractions(kedHeavyRepoManager);
        // mock repo manager
        // Return mock data on call of GET endpoint
        // verify no more interactions of repo manager
    }
}
