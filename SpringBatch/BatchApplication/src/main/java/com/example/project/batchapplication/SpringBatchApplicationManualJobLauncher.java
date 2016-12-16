package com.example.project.batchapplication;

import com.example.project.batchapplication.config.MySpringBatchJobConfiguration;
import com.example.project.common.config.CommonDataConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

/**
 * This launch controller class is designed to manually execute the application.
 *
 * @author mlglenn.
 */
@ContextConfiguration(classes = {MySpringBatchJobConfiguration.class, CommonDataConfiguration.class})
@Controller
@Profile({"local", "dev", "qatest", "staging", "production"})
public class SpringBatchApplicationManualJobLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchApplicationManualJobLauncher.class);
    private static final String MANUAL_LAUNCH_URL = "/Launch.html";
    private static final String MANUAL_EXECUTION = "MANUAL";

    @Value("${application.page.url.success}")
    private String successPage;
    @Value("${application.page.url.error}")
    private String errorPage;

    @Autowired
    private SpringBatchApplicationScheduler springBatchApplicationScheduler;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;


    /**
     * Public method to handle the manual job execution URL request. Spring will execute this
     * method when the URI matches the value in @RequestMapping. After manual job execution,
     * Spring will forward to the page indicated by RedirectView().
     * <p>
     * @return {@link RequestMapping}
     * </p>
     */
    /* Example: https://<hostname>:<port>/orderprocessor/Launch.html */
    @RequestMapping(MANUAL_LAUNCH_URL)
    public RedirectView handle()  {

        try {
            LOGGER.trace("*******************************");
            LOGGER.trace("*** EXECUTING MANUAL LAUNCH ***");
            LOGGER.trace("*******************************");
            LOGGER.trace("Date: {}", getDateParam());

            JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
            JobParameters jobParameters;
            jobParametersBuilder.addString("date", getDateParam());
            jobParametersBuilder.addString("type", MANUAL_EXECUTION);
            jobParameters = jobParametersBuilder.toJobParameters();
            jobLauncher.run(job, jobParameters);

            LOGGER.trace("Date: {}", getDateParam());
            LOGGER.trace("****************************************");
            LOGGER.trace("*** FINISHED EXECUTING MANUAL LAUNCH ***");
            LOGGER.trace("****************************************");

            return new RedirectView(successPage, true);

        } catch (Exception e) {

            LOGGER.trace("*** EXCEPTION THROWN WHILE EXECUTING MANUAL LAUNCH ***");
            LOGGER.trace(e.toString());
            return new RedirectView(errorPage, true);
        }
    }


    private static String getDateParam() {
        return new Date().toString();
    }

}
