package com.example.project.batchapplication;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This is the Spring Batch Scheduler that executes via a Spring "crontab", as annotated by "@Scheduled".
 *
 * @author mlglenn.
 */
@Component
public class SpringBatchApplicationScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchApplicationScheduler.class);

    @Value("${spring.profiles.active}")
    private String springProfile;

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;


    /**
     * This run() method executes according to the @Scheduled annotation and builds a job execution.
     *
     * Example: Scheduled(cron=0 0 13 * * * MON-FRI")
     */
    @Scheduled(cron = "${application.scheduler.crontab}")
    public final void run() {

        LOGGER.info("*******************************************************************");
        LOGGER.info("*** BEGIN PROCESSING");
        LOGGER.info("*******************************************************************");
        LOGGER.info("*** SpringBatchApplicationScheduler.run() entry ***");

        String dateParam = new Date().toString(); //we want this date string as a job parm to make the execution unique
        String jobIdParm = "My Application Id";   //TODO you can use a static id for your application

        BatchStatus status = buildJobExecution(jobLauncher, job, dateParam, jobIdParm);
        LOGGER.info("Execution Status: {}", status);
    }


    private BatchStatus buildJobExecution(final JobLauncher jobLauncher,
                                          final Job job,
                                          final String dateParam,
                                          final String jobIdParm) {

        if ((jobLauncher == null) || (job == null) || (StringUtils.isEmpty(dateParam)) || (StringUtils.isEmpty(jobIdParm))) {
            return BatchStatus.FAILED;
        }

        try {
            JobExecution jobExecution;
            jobExecution = jobLauncher.run(job, buildJobParameters(dateParam, jobIdParm));
            LOGGER.info("Execution Status: {}", jobExecution.getStatus());
            return jobExecution.getStatus();

        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException e) {
            LOGGER.error("JOB ALREADY RUNNING");
            e.printStackTrace();
            return BatchStatus.STARTED;

        } catch (JobRestartException | JobParametersInvalidException | DuplicateKeyException
                | BadSqlGrammarException | IllegalStateException e) {
            LOGGER.debug("Exception caught: {}", e.toString());

            // TODO log and throw appropriate exception
        }
        return BatchStatus.FAILED;
    }

    private JobParameters buildJobParameters(final String dateParam, final String jobIdParam) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        JobParameters jobParameters;
        jobParametersBuilder.addString("date", StringUtils.isEmpty(dateParam) ? " " : dateParam);
        jobParametersBuilder.addString("type", StringUtils.isEmpty(jobIdParam) ? " " : jobIdParam);
        jobParameters = jobParametersBuilder.toJobParameters();
        return jobParameters;
    }

    public String getSpringProfile() {
        return springProfile;
    }

    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    public Job getJob() {
        return job;
    }

}
