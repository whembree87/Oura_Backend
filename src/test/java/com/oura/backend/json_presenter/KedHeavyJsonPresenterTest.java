package com.oura.backend.json_presenter;

import com.oura.backend.entity.KedHeavyEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class KedHeavyJsonPresenterTest {

    @Test
    public void fromReturnsTheExpectedJsonPresentationFromTheGivenEntities() throws IOException {
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

        KedHeavyJsonPresenter expectedJsonPresentation =
                new KedHeavyJsonPresenter(
                        id,
                        week,
                        date,
                        weight,
                        set1,
                        set2,
                        set3,
                        set4,
                        set5,
                        set6,
                        set7,
                        set8,
                        set9,
                        notes
                );

        List<KedHeavyJsonPresenter> expectedJsonListPresentation =
                Collections.singletonList(expectedJsonPresentation);

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
        List<KedHeavyEntity> mockEntities = Collections.singletonList(mockEntity);

        List<KedHeavyJsonPresenter> actualJsonListPresentation = KedHeavyJsonPresenter.from(mockEntities);

        assertThat(actualJsonListPresentation).isEqualTo(expectedJsonListPresentation);
    }
}
