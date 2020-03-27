package com.spring.batch.multipledatasoures.demo.controller;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import com.spring.batch.multipledatasoures.demo.service.FeedGenerationWorkerStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operations")
@Slf4j
public class OperationsController {

    private final FeedGenerationWorkerStatusService feedGenerationWorkerStatusService;

    public OperationsController(FeedGenerationWorkerStatusService feedGenerationWorkerStatusService, JobLauncher jobLauncher, Job job) {
        this.feedGenerationWorkerStatusService = feedGenerationWorkerStatusService;
    }

    /**
     * http://localhost:8082/operations
     */
    @GetMapping
    public List<FeedGenerationWorkerStatus> get() {
        return feedGenerationWorkerStatusService.getAll();
    }


    /**
     * http://localhost:8082/operations/load
     * */
    @GetMapping("/load")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return feedGenerationWorkerStatusService.runBatch();
    }


}
