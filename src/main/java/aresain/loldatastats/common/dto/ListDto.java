package aresain.loldatastats.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListDto<T> {
    private List<T> items;
    private int totalCount;

    public static <T> ListDto<T> of(List<T> items) {
        return new ListDto<>(items, items.size());
    }
} 