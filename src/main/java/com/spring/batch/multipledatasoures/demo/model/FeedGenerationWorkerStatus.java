package com.spring.batch.multipledatasoures.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "feed_generation_worker_status")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuppressWarnings("PMD")
public class FeedGenerationWorkerStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "parent_id")
  private String parentId;

  @Column(name = "task_id")
  private String taskId;

  @Column(name = "locale")
  private String locale;


  @Column(name = "brand")
  private String brand;

  @Column(name = "feed_generation_flow")
  private String feedGenerationFlow;

  @Column(name = "feed_generation_type")
  private String feedGenerationType;


  @Column(name = "execution_status")
  private String executionStatus;

  @Column(name = "service_identifier")
  private String serviceIdentifier;

  @CreationTimestamp
  @Column(name = "create_on", updatable = false)
  private LocalDateTime createdOn;

  @Column(name = "excepted_complete_date")
  private LocalDateTime exceptedDate;

  @Column(name = "triggered_by")
  private String triggeredBy;

  @Column(name = "market")
  private String market;


  @Column(name = "feedGenerationDataType")
  private String feedGenerationDataType;
}
