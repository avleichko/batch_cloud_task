package com.spring.batch.multipledatasoures.demo.config;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<FeedGenerationWorkerStatus, FeedGenerationWorkerStatus> {

    @Override
    public FeedGenerationWorkerStatus process(FeedGenerationWorkerStatus feedGenerationWorkerStatus) throws Exception {
        feedGenerationWorkerStatus.setTriggeredBy("Oleksandr Velichko");
        feedGenerationWorkerStatus.setMarket("UA");
        feedGenerationWorkerStatus.setMarket("US");
        return feedGenerationWorkerStatus;
    }
}
