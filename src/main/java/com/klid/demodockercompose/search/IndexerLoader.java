package com.klid.demodockercompose.search;

import com.klid.demodockercompose.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Kaptue
 */
@RequiredArgsConstructor
@Component
public class IndexerLoader implements ApplicationRunner {

    private final StudentRepository studentRepository;
    private final StudentSearchIndexer indexer;

    @Async
    @Override
    public void run(ApplicationArguments args) {
        // studentRepository.findAll().forEach(indexer::indexe);
    }
}
