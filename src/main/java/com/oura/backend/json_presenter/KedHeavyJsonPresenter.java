package com.oura.backend.json_presenter;

import com.oura.backend.entity.KedHeavyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class KedHeavyJsonPresenter {
    private String id;
    private int week;
    private String date;
    private int weight;
    private int set1;
    private int set2;
    private int set3;
    private int set4;
    private int set5;
    private int set6;
    private int set7;
    private int set8;
    private int set9;
    private String notes;

    private static KedHeavyJsonPresenter from(KedHeavyEntity entity) {
        return KedHeavyJsonPresenter.builder()
                .id(entity.getId())
                .week(entity.getWeek())
                .date(entity.getDate())
                .weight(entity.getWeight())
                .set1(entity.getSet1())
                .set2(entity.getSet2())
                .set3(entity.getSet3())
                .set4(entity.getSet4())
                .set5(entity.getSet5())
                .set6(entity.getSet6())
                .set7(entity.getSet7())
                .set8(entity.getSet8())
                .set9(entity.getSet9())
                .notes(entity.getNotes())
                .build();
    }

    public static List<KedHeavyJsonPresenter> from(List<KedHeavyEntity> entities) {
        List<KedHeavyJsonPresenter> list = new ArrayList<>();

        for (KedHeavyEntity entity: entities) {
            KedHeavyJsonPresenter from = from(entity);
            list.add(from);
        }

        return list;
    }
}
