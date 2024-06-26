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
  
  private final ManagementService service;

  public CamundaMonitoringMetrics(ProcessEngine engine) {
    super();
    Objects.requireNonNull(engine);
    this.service = engine.getManagementService();
  }

  
  
  
 // License Metrics
  @Bean
  public Gauge rootProcessInstanceStart(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.ROOT_PROCESS_INSTANCE_START);

    return Gauge.builder("engine.root.PI.start", query::sum)
        .description("Number of executed Root Process Instance executions")
        .tags("CamundaOOTB","MetricLicensePIMetric")
        .register(registry);
  }

  @Bean
  public Gauge executedDecisionInstances(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.EXECUTED_DECISION_INSTANCES);

    return Gauge.builder("engine.executed.decision.instances", query::sum)
        .description("Number of executed Decision Instance executions")
        .tags("CamundaOOTB","MetricLicenseDIMetric")
        .register(registry);
  }
  
  @Bean
  public Gauge uniqueTaskWorkers(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.UNIQUE_TASK_WORKERS);

    return Gauge.builder("engine.unique.task.workers", query::sum)
        .description("Number of executed Decision Instance executions")
        .tags("CamundaOOTB","MetricLicenseUTMetric")
        .register(registry);
  }
 
 // housekeeping metrics

@Bean
public Gauge historyCleanupRemovedProcessInstances(MeterRegistry registry) {
  MetricsQuery query = service.createMetricsQuery().name(Metrics.HISTORY_CLEANUP_REMOVED_PROCESS_INSTANCES);

  return Gauge.builder("engine.cleanup.PI", query::sum)
      .description("Number of cleaned Process Instances")
      .tags("CamundaOOTB","MetricHousekeeping")
      .register(registry);
}

@Bean
public Gauge historyCleanupRemovedDecisionInstances(MeterRegistry registry) {
  MetricsQuery query = service.createMetricsQuery().name(Metrics.HISTORY_CLEANUP_REMOVED_DECISION_INSTANCES);

  return Gauge.builder("engine.cleanup.DI", query::sum)
      .description("Number of cleanes Decision Instances")
      .tags("CamundaOOTB","MetricHousekeeping")
      .register(registry);
}

@Bean
public Gauge historyCleanupRemovedTasks(MeterRegistry registry) {
  MetricsQuery query = service.createMetricsQuery().name(Metrics.HISTORY_CLEANUP_REMOVED_TASK_METRICS);

  return Gauge.builder("engine.cleanup.task", query::sum)
      .description("Number of cleaned Tasks")
      .tags("CamundaOOTB","MetricHousekeeping")
      .register(registry);
}

// Flow Node Instances

@Bean
public Gauge activityInstanceStart(MeterRegistry registry) {
  MetricsQuery query = service.createMetricsQuery().name(Metrics.ACTIVTY_INSTANCE_START);

  return Gauge.builder("engine.activity.instance.start", query::sum)
      .description("The number of flow node instances (activity instances) started (FNI)")
      .tags("CamundaOOTB","FNIMetric")
      .register(registry);
}

@Bean
public Gauge activityInstanceEnd(MeterRegistry registry) {
  MetricsQuery query = service.createMetricsQuery().name(Metrics.ACTIVTY_INSTANCE_END);

  return Gauge.builder("engine.activity.instance.start", query::sum)
      .description("The number of flow node instances (activity instances) ended (FNI)")
      .tags("CamundaOOTB","FNIMetric")
      .register(registry);
}


  

// General Metrics




  
  @Bean
  public Gauge jobExecutionsSuccessful(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_SUCCESSFUL);

    return Gauge.builder("engine.job.successful", query::sum)
        .description("Job successful")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }
  
  @Bean
  public Gauge jobExecutionsFailed(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_FAILED);

    return Gauge.builder("engine.job.executions.failed", query::sum)
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

    return Gauge.builder("engine.job.acquisitions.attempted", query::sum)
        .description("Performed job acquisition cycles")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge jobAcquisitionsSuccessful(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_ACQUIRED_SUCCESS);

    return Gauge.builder("engine.job.acquisitions.successful", query::sum)
        .description("Successful job acquisitions")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }
  
  @Bean
  public Gauge jobAcquistionsFailed(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_ACQUIRED_FAILURE);

    return Gauge.builder("engine.job.acquisitions.failed", query::sum)
        .description("Failed job acquisitions")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge jobLocksExclusive(MeterRegistry registry) {
    MetricsQuery query = service.createMetricsQuery().name(Metrics.JOB_LOCKED_EXCLUSIVE);

    return Gauge.builder("engine.job.locks.exclusive", query::sum)
        .description("Exclusive jobs that are immediately locked and executed")
        .tags("CamundaOOTB","TESTCustomMetric")
        .register(registry);
  }

  @Bean
  public Gauge dueJobsInDB(MeterRegistry registry) {
    Query jobQuery = service.createJobQuery().executable().messages();

    return Gauge.builder("engine.jobs.due", jobQuery::count)
        .description("Jobs from async continuation that are due").register(registry);
  }
}
