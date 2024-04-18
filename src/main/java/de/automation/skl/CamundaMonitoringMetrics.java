package de.automation.skl;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Objects;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.management.Metrics;
import org.camunda.bpm.engine.management.MetricsQuery;
import org.camunda.bpm.engine.query.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CamundaMonitoringMetrics {
  
	
  private static final String NUMBER_OF_EXCLUSIVE_JOBS = "Number of exclusive jobs";
  private static final String NUMBER_OF_ACQUISITION_CYCLES = "Number of acquisition cycles";
  private static final String NUMBER_OF_JOBS = "Number of jobs";

  private final ManagementService service;
  
   

  public CamundaMonitoringMetrics(ProcessEngine engine) {
    super();
    Objects.requireNonNull(engine);
    this.service = engine.getManagementService();
  }

  @Bean
  public Gauge rootProcessInstanceStart(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.ROOT_PROCESS_INSTANCE_START);

    return Gauge.builder("root.PI.start", query::sum)
        .description("Number of executed Root Process Instance executions")
        .tags("CamundaOOTB","MetricLicensePIMetric")
        .register(registry);
  }

  @Bean
  public Gauge executedDecisionInstances(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.EXECUTED_DECISION_INSTANCES);

    return Gauge.builder("executed.decision.instances", query::sum)
        .description("Number of executed Decision Instance executions")
        .tags("CamundaOOTB","MetricLicenseDIMetric")
        .register(registry);
  }
  
  @Bean
  public Gauge uniqueTaskWorkers(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.UNIQUE_TASK_WORKERS);

    return Gauge.builder("unique.task.workers", query::sum)
        .description("Number of executed Decision Instance executions")
        .tags("CamundaOOTB","MetricLicenseUTMetric")
        .register(registry);
  }
  
  
  @Bean
  public Gauge jobExecutionsSuccessful(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_SUCCESSFUL);

    return Gauge.builder("job.successful", query::sum)
        .description("Job successful")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }
  
  @Bean
  public Gauge jobExecutionsFailed(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_FAILED);

    return Gauge.builder("job.executions.failed", query::sum)
        .description("Failed job executions")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge jobExecutionsRejected(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_EXECUTION_REJECTED);

    return Gauge.builder("job.executions.rejected", query::sum)
        .description("Rejected jobs due to saturated execution resources")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge jobAcquisitionsAttempted(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_ACQUISITION_ATTEMPT);

    return Gauge.builder("job.acquisitions.attempted", query::sum)
        .description("Performed job acquisition cycles")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge jobAcquisitionsSuccessful(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_ACQUIRED_SUCCESS);

    return Gauge.builder("job.acquisitions.successful", query::sum)
        .description("Successful job acquisitions")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }
  
  @Bean
  public Gauge jobAcquistionsFailed(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_ACQUIRED_FAILURE);

    return Gauge.builder("job.acquisitions.failed", query::sum)
        .description("Failed job acquisitions")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge jobLocksExclusive(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_LOCKED_EXCLUSIVE);

    return Gauge.builder("job.locks.exclusive", query::sum)
        .description("Exclusive jobs that are immediately locked and executed")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge dueJobsInDB(MeterRegistry registry) {
    Query jobQuery = service.createJobQuery().executable().messages();

    return Gauge.builder("jobs.due", jobQuery::count)
        .description("Jobs from async continuation that are due").register(registry);
  }
}
