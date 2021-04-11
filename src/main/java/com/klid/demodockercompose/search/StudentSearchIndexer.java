package com.klid.demodockercompose.search;

import com.klid.demodockercompose.entity.Student;
import org.springframework.data.domain.Page;

/**
 * @author Ivan Kaptue
 */
public interface StudentSearchIndexer {

    void indexe(Student student);

    void remove(long id);

    Page<Student> search(String q, int page, int size);

}
