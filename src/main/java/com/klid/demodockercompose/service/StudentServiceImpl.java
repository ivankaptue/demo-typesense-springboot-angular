package com.klid.demodockercompose.service;

import com.klid.demodockercompose.entity.Student;
import com.klid.demodockercompose.event.StudentCreatedEvent;
import com.klid.demodockercompose.event.StudentDeletedEvent;
import com.klid.demodockercompose.event.StudentUpdatedEvent;
import com.klid.demodockercompose.exception.StudentNotFoundException;
import com.klid.demodockercompose.repository.StudentRepository;
import com.klid.demodockercompose.search.StudentSearchIndexer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Ivan Kaptue
 */
@RequiredArgsConstructor
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ApplicationEventPublisher publisher;
    private final StudentSearchIndexer indexer;

    /**
     * Create student
     *
     * @param student Student to create
     * @return Created student
     */
    @Override
    public Student create(Student student) {
        student.setId(null);
        studentRepository.save(student);

        publisher.publishEvent(new StudentCreatedEvent(student));

        return student;
    }

    /**
     * Update student
     *
     * @param id      Id of student to update
     * @param student Student to update
     * @return Updated student
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     * @throws StudentNotFoundException if student with {@literal id} not found.
     */
    @Override
    public Student update(Long id, Student student) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Student id must not be null");
        }
        Student s = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(String.format("Student with id %d not found", id)));

        s.setFirstname(student.getFirstname());
        s.setLastname(student.getLastname());
        s.setEmail(student.getEmail());
        s.setSchool(student.getSchool());

        publisher.publishEvent(new StudentUpdatedEvent(s));

        return s;
    }

    /**
     * Delete student
     *
     * @param id Id of student to delete
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    public void delete(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Student id must not be null");
        }
        Student s = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(String.format("Student with id %d not found", id)));
        studentRepository.delete(s);

        publisher.publishEvent(new StudentDeletedEvent(id));
    }

    /**
     * Find student by {@literal id}
     *
     * @param id Id of student to find
     * @return Found student
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     * @throws StudentNotFoundException if student with {@literal id} not found.
     */
    @Override
    public Student findById(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Student id must not be null");
        }
        return studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(String.format("Student with id %d not found", id)));
    }

    /**
     * Find students
     *
     * @param page current page
     * @param size size of page
     * @return list of students
     */
    @Override
    public Page<Student> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    /**
     * Search student from search engine
     *
     * @param query query
     * @param page  page
     * @param size  size per page
     * @return found students
     */
    @Override
    public Page<Student> search(String query, int page, int size) {
        return indexer.search(query, page, size);
    }

}
