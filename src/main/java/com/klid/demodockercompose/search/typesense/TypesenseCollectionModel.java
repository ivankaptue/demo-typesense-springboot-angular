package com.klid.demodockercompose.search.typesense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Kaptue
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypesenseCollectionModel {
    private String name;
    private List<TypesenseCollectionFieldModel> fields = new ArrayList<>();
    private String default_sorting_field;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TypesenseCollectionFieldModel {
        private String name;
        private String type;
        private boolean facet;
    }
}
