package com.example.project.batchapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the main application class for a Spring Boot application.
 *
 * @author mlglenn.
 */
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SpringBatchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchApplication.class);

    /**
     * This method loads the application context and executes when the application is deployed.
     *
     * @param args {@link String[]}
     */
    public static void main(String[] args) {

        LOGGER.debug("*** SpringBatchApplication.main() executing ***");

        ApplicationContext ctx = SpringApplication.run(SpringBatchApplication.class, args);
    }

}
