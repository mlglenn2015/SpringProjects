package com.example.project.batchapplication.processor;

import com.example.project.common.domain.MyDataTransferObject;
import com.example.project.common.exception.MyApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Spring Batch Processor class //TODO .
 *
 * @author mlglenn.
 */
@Component
public class MyApplicationProcessor implements ItemProcessor<MyDataTransferObject, MyDataTransferObject> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationProcessor.class);
    private int recordCount = 0;



    /**
     * Main method TODO.
     * <p>
     * @param inputDto {@link MyDataTransferObject}
     * @return {@link MyDataTransferObject}
     * @throws MyApplicationException
     * </p>
     */
    public MyDataTransferObject process(final MyDataTransferObject inputDto) throws MyApplicationException {

        LOGGER.debug("*** MyApplicationProcessor.process(): ENTRY ***");
        ++recordCount;

        /* TODO Perform Validation First */


        /* TODO other stuff */


        return inputDto;
    }

}
