package com.klid.demodockercompose.search.typesense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Kaptue
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypesenseStudentDocument {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String school;
    private long id_value;
}
