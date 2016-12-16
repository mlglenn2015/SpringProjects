package com.example.project.batchapplication.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This class listens to the job execution and can be enhanced to perform tasks either
 * before or after Spring Batch job execution. The job listener is critical to monitoring
 * the health of the application.
 *
 * @author MLGlenn.
 */
@Component
public class MyJobListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyJobListener.class);
    private static final String END_STATUS = "*** END JOB EXECUTION: MyJobListener.afterJob()";
    private static final String COMPLETED = "COMPLETED";
    private static final String FAILED = "FAILED";

    @Autowired
    private ApplicationContext applicationContext;



    /**
     * Event that takes place before job execution.
     * @param jobExecution {@link JobExecution}
     */
    @Override
    public void beforeJob(final JobExecution jobExecution) {
        LOGGER.debug("*** MyJobListener - beforeJob() ***");
    }

    /**
     * Event that takes place after job execution.
     * <p>
     *     This class will send email to a designated list of recipients when enabled in
     *     each environment.
     * </p>
     * @param jobExecution {@link JobExecution}
     */
    @Override
    public void afterJob(final JobExecution jobExecution) {
        LOGGER.debug(END_STATUS);
        LOGGER.debug("*** MyJobListener - afterJob() ***");
        LOGGER.debug("EXIT STATUS: {}", jobExecution.getExitStatus());


        //TODO send email, log info, whatever

    }

}
