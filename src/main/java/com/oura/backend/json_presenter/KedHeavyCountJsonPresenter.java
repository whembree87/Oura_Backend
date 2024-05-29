package com.oura.backend.json_presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class KedHeavyCountJsonPresenter {
    private long count;

    public static KedHeavyCountJsonPresenter from(long count) {
        return KedHeavyCountJsonPresenter.builder()
                .count(count)
                .build();
    }
}
