package com.klid.demodockercompose.search.typesense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Kaptue
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypesenseStudentSearchResult {
    private int found;
    private List<HitItem> hits = new ArrayList<>();
    private int search_time_ms;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HitItem {
        private TypesenseStudentDocument document;
    }
}
