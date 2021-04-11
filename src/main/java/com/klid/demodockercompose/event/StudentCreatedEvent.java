package com.klid.demodockercompose.event;

import com.klid.demodockercompose.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ivan Kaptue
 */
@AllArgsConstructor
@Getter
public class StudentCreatedEvent {
    private final Student student;
}
