package com.spring.batch.multipledatasoures.demo.config;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import com.spring.batch.multipledatasoures.demo.repository.WorkerStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class Writer implements ItemWriter<FeedGenerationWorkerStatus> {

    @Autowired
    WorkerStatusRepository repository;

    @Override
    public void write(List<? extends FeedGenerationWorkerStatus> list) throws Exception {
        list.forEach(o -> {
            log.error(o.toString());
        });
    }
}
