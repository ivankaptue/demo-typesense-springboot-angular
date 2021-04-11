package com.klid.demodockercompose.controller;

import com.klid.demodockercompose.entity.Student;
import com.klid.demodockercompose.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ivan Kaptue
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> create(
        @Valid @RequestBody Student student
    ) {
        var result = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("{id:[0-9]+}")
    public ResponseEntity<Student> update(
        @PathVariable Long id,
        @Valid @RequestBody Student student
    ) {
        var result = studentService.update(id, student);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity<Student> findById(
        @PathVariable Long id
    ) {
        var result = studentService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<Student>> findAll(
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        var result = studentService.findAll(page - 1, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search")
    public ResponseEntity<Page<Student>> search(
        @RequestParam(value = "q", required = false, defaultValue = "*") String query,
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        var result = studentService.search(query, page, size);
        return ResponseEntity.ok(result);
    }

}
