package com.spring.batch.multipledatasoures.demo.config;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import com.spring.batch.multipledatasoures.demo.repository.WorkerStatusRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    /**
     * Step 1. create job
     * <p>
     * final Job job = jobBuilderFactory.get("ETL-LOAD")
     * .incrementer(new RunIdIncrementer())
     * .start(step)
     * .build();
     * <p>
     * Step 2. create step skeleton
     * <p>
     * Step step = stepBuilderFactory.get("ETL-db-load-step")
     * .chunk(100) //  size of elements that needs to be parsed
     * .reader()            // method responcible for read
     * .processor()         // method responcible for process
     * .writer()            // methods that perform an actual migration
     * .build();
     * <p>
     * Step 3.
     */

    @Autowired
    WorkerStatusRepository repository;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<FeedGenerationWorkerStatus> itemReader,
                   ItemProcessor<FeedGenerationWorkerStatus, FeedGenerationWorkerStatus> itemProcessor,
                   ItemWriter<FeedGenerationWorkerStatus> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-db-load-step")
                .<FeedGenerationWorkerStatus, FeedGenerationWorkerStatus>chunk(500)  //  size of elements that needs to be parsed
                .reader(itemReader)                                                           // method responcible for read
                .processor(itemProcessor)                                                     // method responcible for process
                .writer(itemWriter)                                                           // methods that perform an actual migration
                .build();


        final Job job = jobBuilderFactory.get("ETL-LOAD")
                .incrementer(new RunIdIncrementer()) // this is something lide ids of eacj jobe execution
                //.flow(step2)                          in case of many steps
                //.next(step3)
                .start(step)
                .build();

        return job;
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
