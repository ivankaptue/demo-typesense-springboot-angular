package com.klid.demodockercompose.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ivan Kaptue
 */
@AllArgsConstructor
@Getter
public class StudentDeletedEvent {
    private final long id;
}
