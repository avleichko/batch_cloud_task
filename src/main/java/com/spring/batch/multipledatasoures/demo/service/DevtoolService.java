package com.spring.batch.multipledatasoures.demo.service;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import com.spring.batch.multipledatasoures.demo.repository.WorkerStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DevtoolService {

    private final WorkerStatusRepository workerStatusRepository;

    public DevtoolService(WorkerStatusRepository workerStatusRepository) {
        this.workerStatusRepository = workerStatusRepository;
    }


    public void initDatabase() {
        log.info("adding record to the database");
        List<FeedGenerationWorkerStatus> statusList = new ArrayList<>();
        for(int i=0; i<10000; i++) {
            FeedGenerationWorkerStatus status = new FeedGenerationWorkerStatus();
            status.setBrand("mck");
            status.setTriggeredBy("Alex");
            status.setFeedGenerationType("OLAPIC");
            status.setFeedGenerationFlow("FULL");
            status.setMarket("US");
            status.setExecutionStatus("ok");
            statusList.add(status);
        }
        log.info("1000 records has been added to database");
        workerStatusRepository.saveAll(statusList);
    }
}
