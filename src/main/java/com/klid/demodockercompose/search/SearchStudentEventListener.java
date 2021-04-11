package com.klid.demodockercompose.search;

import com.klid.demodockercompose.event.StudentCreatedEvent;
import com.klid.demodockercompose.event.StudentDeletedEvent;
import com.klid.demodockercompose.event.StudentUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Kaptue
 */
@RequiredArgsConstructor
@Component
public class SearchStudentEventListener {

    private final StudentSearchIndexer indexer;

    @Async
    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {
        System.out.println(event);
        indexer.indexe(event.getStudent());
    }

    @Async
    @EventListener
    public void handleStudentUpdated(StudentUpdatedEvent event) {
        System.out.println(event);
        indexer.indexe(event.getStudent());
    }

    @Async
    @EventListener
    public void handleStudentDeleted(StudentDeletedEvent event) {
        System.out.println(event);
        indexer.remove(event.getId());
    }

}
