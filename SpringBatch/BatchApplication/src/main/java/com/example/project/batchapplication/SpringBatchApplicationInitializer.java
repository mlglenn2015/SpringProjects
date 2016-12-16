package com.example.project.batchapplication;

//import org.springframework.boot.context.web.SpringBootServletInitializer; deprecated
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * This Spring Boot class sets up the application for execution within a web container.
 *
 * @author mlglenn.
 */
public class SpringBatchApplicationInitializer extends SpringBootServletInitializer
        implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchApplicationInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        LOGGER.debug("*** SpringBatchApplicationInitializer.configure(): ENTRY ***");

        return application.sources(SpringBatchApplication.class);
    }
}
