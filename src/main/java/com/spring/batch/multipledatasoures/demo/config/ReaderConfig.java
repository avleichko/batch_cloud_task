package com.spring.batch.multipledatasoures.demo.config;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import com.spring.batch.multipledatasoures.demo.repository.WorkerStatusRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReaderConfig {

    private final WorkerStatusRepository repository;

    public ReaderConfig(WorkerStatusRepository repository) {
        this.repository = repository;
    }

    @Bean
    public RepositoryItemReader<FeedGenerationWorkerStatus> itemReader() {
        RepositoryItemReader<FeedGenerationWorkerStatus> reader = new RepositoryItemReader<>();
        reader.setRepository(repository);
        reader.setMethodName("findAll");
        reader.setName("spring-data-repos-reader");
        Map<String, Sort.Direction> sort = new HashMap<String, Sort.Direction>();
        sort.put("id", Sort.Direction.ASC);
        reader.setSort(sort);

        reader.setSort(sort);
        return reader;
    }
}
