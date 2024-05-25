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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    }

    @Test
    public void getPagedKedHeavyReturns200AndListOfEntitiesOnSuccess() throws Exception {
        String id = "123";
        int week = 1;
        String date = "2024-04-18 00:00:00";
        int weight = 0;
        int set1 = 0;
        int set2 = 0;
        int set3 = 0;
        int set4 = 0;
        int set5 = 0;
        int set6 = 0;
        int set7 = 0;
        int set8 = 0;
        int set9 = 0;
        String notes = "blah";

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
        Page<KedHeavyEntity> MockPagedEntity = new PageImpl<>(Collections.singletonList(mockEntity));

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

        when(kedHeavyRepoManager.getPagedKedHeavy(anyInt(), anyInt())).thenReturn(MockPagedEntity);

        MvcResult result = mockMvc
                .perform((get("/getpagedkedheavy"))
                        .param("page", "0")
                        .param("size", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(Collections.singletonList(expectedResult)));

        verify(kedHeavyRepoManager, times(1)).getPagedKedHeavy(0, 1);
        verifyNoMoreInteractions(kedHeavyRepoManager);
    }

    @Test
    public void getPagedKedHeavyReturnsTheExpectedJson() throws Exception {
        String id = "123";
        int week = 1;
        String date = "2024-04-18 00:00:00";
        int weight = 0;
        int set1 = 0;
        int set2 = 0;
        int set3 = 0;
        int set4 = 0;
        int set5 = 0;
        int set6 = 0;
        int set7 = 0;
        int set8 = 0;
        int set9 = 0;
        String notes = "blah";

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
        Page<KedHeavyEntity> mockPagedEntity = new PageImpl<>(Collections.singletonList(mockEntity));
        String expectedJson = "[{\"id\":\"123\",\"week\":1,\"date\":\"2024-04-18 00:00:00\",\"weight\":0,\"set1\":0,\"set2\":0,\"set3\":0,\"set4\":0,\"set5\":0,\"set6\":0,\"set7\":0,\"set8\":0,\"set9\":0,\"notes\":\"blah\"}]";

        when(kedHeavyRepoManager.getPagedKedHeavy(anyInt(), anyInt()))
                .thenReturn(mockPagedEntity);

        MvcResult result = mockMvc
                .perform((get("/getpagedkedheavy"))
                        .param("page", "0")
                        .param("size", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);

        verify(kedHeavyRepoManager, times(1)).getPagedKedHeavy(0, 1);
        verifyNoMoreInteractions(kedHeavyRepoManager);
    }

    @Test
    public void getKedHeavyCountReturnsTheTotalRecordCount() throws Exception {
        when(kedHeavyRepoManager.getKedHeavyCount()).thenReturn(10L);

        MvcResult result = mockMvc
                .perform((get("/getkedheavycount")))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("10");

        verify(kedHeavyRepoManager, times(1)).getKedHeavyCount();
        verifyNoMoreInteractions(kedHeavyRepoManager);
    }
}
