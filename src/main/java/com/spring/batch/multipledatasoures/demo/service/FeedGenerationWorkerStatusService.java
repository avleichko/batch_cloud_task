package com.spring.batch.multipledatasoures.demo.service;

import com.spring.batch.multipledatasoures.demo.exception.CommonBatchServiceException;
import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import com.spring.batch.multipledatasoures.demo.repository.WorkerStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FeedGenerationWorkerStatusService {

    private final WorkerStatusRepository workerStatusRepository;

    private final JobLauncher jobLauncher;

    private final Job job;

    public FeedGenerationWorkerStatusService(WorkerStatusRepository workerStatusRepository, JobLauncher jobLauncher, Job job) {
        this.workerStatusRepository = workerStatusRepository;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    /**
     * We can also use @CircuitBraker annotation from spring retry library
     *
     * */
    //TODO uncommentMe when will show demo
    //@Scheduled(cron = "${batch.cron}")
    @Retryable(value = {CommonBatchServiceException.class})
    public BatchStatus runBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        if(Math.random() >.5){
            throw new CommonBatchServiceException("something went wrong");
        }

        final JobExecution run = jobLauncher.run(job,
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());

        while (run.isRunning()){
            log.warn("job is running ");
        }

        log.warn(job.getName());
        log.warn(job.toString());

        return run.getStatus();
    }

    /**
     * Method can be used in case if we met some unexpected errors
     *
     * */
    @Recover
    public BatchStatus recover(CommonBatchServiceException e){
        log.error("recovered");
        log.error("recovered");
        log.error("recovered");
        log.error("recovered");
        return BatchStatus.STOPPED;
    }

    public List<FeedGenerationWorkerStatus> getAll(){
        return workerStatusRepository.findAll();
    }
}
