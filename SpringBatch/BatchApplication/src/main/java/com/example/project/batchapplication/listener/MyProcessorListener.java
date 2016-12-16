package com.example.project.batchapplication.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This class reports the status of the {@link com.example.project.batchapplication.processor.MyApplicationProcessor}.
 *
 * @author mlglenn.
 */
@Component
public class MyProcessorListener implements ChunkListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyProcessorListener.class);

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * Event that fires prior to executing {@link com.example.project.batchapplication.processor.MyApplicationProcessor}.
     *
     * @param context @{link ChunkContext}
     */
    @Override
    public void beforeChunk(final ChunkContext context) {

        LOGGER.debug("*** MyProcessorListener.beforeChunk() ***");

    }

    /**
     * Event that fires after executing {@link com.example.project.batchapplication.processor.MyApplicationProcessor}.
     *
     * @param context @{link ChunkContext}
     */
    @Override
    public void afterChunk(final ChunkContext context) {

        LOGGER.debug("*** MyProcessorListener.afterChunk() ***");
        LOGGER.debug("***************************************************************************");
        LOGGER.debug("Read count: {}", context.getStepContext().getStepExecution().getReadCount());

        if ((context.getStepContext().getStepExecution().getReadCount() == 0)) {
            String dateParam = new Date().toString();
            LOGGER.debug("*** {} ***", dateParam);
            LOGGER.debug("*** Record Count was 0 ***");
            LOGGER.error("!!! No records processed !!!");

            //TODO send email, log info, whatever



        }
        LOGGER.debug("***************************************************************************");
    }

    /**
     * Event that fires on error encountered.
     *
     * @param context @{link ChunkContext}
     */
    @Override
    public void afterChunkError(final ChunkContext context) {

        LOGGER.debug("*** MyProcessorListener.afterChunkError() ***");

    }

}
