package com.example.project.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Exception class.
 *
 * @author mlglenn.
 */
public class MyApplicationException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationException.class);
    private static final String APPLICATION_EXCEPTION = "APPLICATION EXCEPTION CAUGHT!";


    /**
     * Default constructor.
     */
    public MyApplicationException() {
        super();
        LOGGER.error(APPLICATION_EXCEPTION);
    }

    /**
     * Constructor with message.
     * @param message String containing exception message
     */
    public MyApplicationException(final String message) {
        super(new StringBuffer()
                .append(APPLICATION_EXCEPTION)
                .append(" -- ")
                .append(message)
                .toString());
        LOGGER.error(APPLICATION_EXCEPTION);
    }

}
