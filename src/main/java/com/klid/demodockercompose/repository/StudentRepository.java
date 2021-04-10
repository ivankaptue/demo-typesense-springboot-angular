package com.klid.demodockercompose.repository;

import com.klid.demodockercompose.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ivan Kaptue
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
