package com.klid.demodockercompose.fixture;

import com.github.javafaker.Faker;
import com.klid.demodockercompose.entity.Student;
import com.klid.demodockercompose.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Kaptue
 */
@RequiredArgsConstructor
@Component
public class StudentFixture implements ApplicationRunner {

    private final StudentRepository studentRepository;

    @Override
    public void run(ApplicationArguments args) {
//        System.out.println("Student fixture loads");
//        Faker faker = new Faker();
//        for (var i = 1; i <= 500000; ++i) {
//            var student = new Student();
//            student.setFirstname(faker.name().firstName());
//            student.setLastname(faker.name().lastName());
//            student.setEmail(faker.internet().emailAddress());
//            student.setSchool(faker.university().name());
//            studentRepository.save(student);
//            System.out.printf("Student %d saved %s%n", i, student.getEmail());
//        }
//        System.out.println("Student fixtures loaded");
    }

}
