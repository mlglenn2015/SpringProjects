package com.example.project.batchapplication.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * This Spring Batch Tasklet performs initialization and housekeeping.
 *
 * @author MLGlenn.
 */
@Component
public class HousekeepingTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HousekeepingTasklet.class);



    /**
     * This method performs house keeping tasks.
     *
     * @param contribution {@link org.springframework.batch.core.StepContribution} contribution
     * @param chunkContext {@link org.springframework.batch.core.scope.context.ChunkContext} chunkContext
     *
     * @return {@link org.springframework.batch.repeat.RepeatStatus}
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
            throws Exception {

        LOGGER.debug("*** HousekeepingTasklet.execute(): ENTRY ***");
        performHousekeepingTasks();
        return RepeatStatus.FINISHED;
    }


    private void performHousekeepingTasks() {

        //TODO do something

        LOGGER.debug("Stubbed HousekeepingTasklet.performHousekeepingTasks()");
    }

}
