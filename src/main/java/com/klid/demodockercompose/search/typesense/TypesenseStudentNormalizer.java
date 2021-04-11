package com.klid.demodockercompose.search.typesense;

import com.klid.demodockercompose.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Kaptue
 */
@Component
public class TypesenseStudentNormalizer {

    public TypesenseStudentDocument normalize(Student student) {
        return new TypesenseStudentDocument(
            String.valueOf(student.getId()),
            student.getFirstname(),
            student.getLastname(),
            student.getEmail(),
            student.getSchool(),
            student.getId()
        );
    }

    public Student normalize(TypesenseStudentDocument document) {
        return new Student(
            Long.valueOf(document.getId()),
            document.getFirstname(),
            document.getLastname(),
            document.getEmail(),
            document.getSchool()
        );
    }

}
