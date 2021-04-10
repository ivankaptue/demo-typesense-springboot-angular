package com.klid.demodockercompose.service;

import com.klid.demodockercompose.entity.Student;
import com.klid.demodockercompose.exception.StudentNotFoundException;
import org.springframework.data.domain.Page;

/**
 * @author Ivan Kaptue
 */
public interface StudentService {
    /**
     * Create student
     *
     * @param student Student to create
     * @return Created student
     */
    Student create(Student student);

    /**
     * Update student
     *
     * @param id      Id of student to update
     * @param student Student to update
     * @return Updated student
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     * @throws StudentNotFoundException if student with {@literal id} not found.
     */
    Student update(Long id, Student student);

    /**
     * Delete student
     *
     * @param id Id of student to delete
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    void delete(Long id);

    /**
     * Find student by {@literal id}
     *
     * @param id Id of student to find
     * @return Found student
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     * @throws StudentNotFoundException if student with {@literal id} not found.
     */
    Student findById(Long id);

    /**
     * Find students
     *
     * @param page current page
     * @param size size of page
     * @return list of students
     */
    Page<Student> findAll(int page, int size);
}
