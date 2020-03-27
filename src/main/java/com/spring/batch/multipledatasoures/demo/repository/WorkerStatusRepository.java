package com.spring.batch.multipledatasoures.demo.repository;

import com.spring.batch.multipledatasoures.demo.model.FeedGenerationWorkerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerStatusRepository extends JpaRepository<FeedGenerationWorkerStatus, Long> {

}
