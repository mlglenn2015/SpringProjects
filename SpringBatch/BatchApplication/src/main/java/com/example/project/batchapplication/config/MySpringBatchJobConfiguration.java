package com.example.project.batchapplication.config;

import com.example.project.batchapplication.SpringBatchApplicationScheduler;
import com.example.project.batchapplication.helper.MyRowMapper;
import com.example.project.batchapplication.listener.MyJobListener;
import com.example.project.batchapplication.listener.MyProcessorListener;
import com.example.project.batchapplication.processor.MyApplicationProcessor;
import com.example.project.batchapplication.tasklet.HousekeepingTasklet;
import com.example.project.common.domain.MyDataTransferObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Optional;

/**
 * This is the Spring Batch job configuration for TODO.
 * <p>
 * Controlled by Spring Batch, this class creates the bean instances for configuration.
 * </p>
 * @author mlglenn.
 */
@Configuration
@Profile({"local", "dev", "qatest", "staging", "production"})
public class MySpringBatchJobConfiguration {


    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBatchJobConfiguration.class);

    private static final String JOB_NAME = "myJob";
    private static final String STEP_INITIALIZE = "jobInitializeStep";
    private static final String STEP_PROCESS_ORDERS = "processOrdersStep";


    @Value("${spring.jpa.show-sql}")
    private String showSql;
    @Value("${application.database.table.chunksize}")
    private int dbReaderChunkSize;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRegistry jobRegistry;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private DataSource datasource;
    @Autowired
    private Environment environment;



    /**********************
     ****    JOB INFO   ***
     **********************/

    /**
     * Spring Batch job configuration for the application.
     * @return {@link org.springframework.batch.core}
     */
    @Bean
    public Job myJob() {

        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(runIdIncrementer())
                .listener(myJobListener())
                .start(jobInitializeStep())
                .next(processOrdersStep())
                .build();
    }


    /**
     * Housekeeping tasks.
     * @return {@link org.springframework.batch.core.Step}
     */
    @Bean
    public Step jobInitializeStep() {
        LOGGER.debug("MySpringBatchJobConfiguration.jobInitializeStep()");
        return stepBuilderFactory.get(STEP_INITIALIZE)
                .tasklet(housekeepingTasklet())
                .build();
    }

    /**
     * This step reads candidate records from the database and processes them.
     * @return {@link org.springframework.batch.core.Step}
     */
    @Bean
    public Step processOrdersStep() {
        return stepBuilderFactory.get(STEP_PROCESS_ORDERS)
                .<MyDataTransferObject, MyDataTransferObject>chunk(dbReaderChunkSize)
                .reader(myItemReader())
                .processor(myApplicationProcessor())
                .listener(myProcessorListener())
                .build();
    }



    /**********************
     ****    READERS   ****
     **********************/

    /**
     * Reads candidate records from the database according to the specified SQL statement.
     * @return @{link ItemStreamReader}
     */
    @Bean
    @StepScope
    public ItemStreamReader<MyDataTransferObject> myItemReader() {
        JdbcCursorItemReader<MyDataTransferObject> reader = new JdbcCursorItemReader<>();
        reader.setRowMapper(myRowMapper());
        reader.setDataSource(datasource);
        //reader.setSaveState(false); //necessary when using the PROCESSED flag in the table
        reader.setSql("SELECT INDEX, COLUMN1, COLUMN2 FROM SAMPLE"); //TODO move SQL stmt to db table
        return reader;
    }

    /**
     * Spring Row mapper class to map result set values to the DTO.
     * @return @{link RowMapper}
     */
    @Bean
    public RowMapper<MyDataTransferObject> myRowMapper() {
        return new MyRowMapper();
    }



    /**********************
     ****  PROCESSORS  ****
     **********************/

    /**
     * Main processor.
     * @return {@link org.springframework.batch.item.ItemProcessor}
     */
    @Bean
    public ItemProcessor<MyDataTransferObject, MyDataTransferObject> myApplicationProcessor() {
        return new MyApplicationProcessor();
    }



    /******************
     **** LISTENERS ****
     ******************/

    /**
     * Listener class for the job execution.
     * @return @{link JobExecutionListener}
     */
    @Bean
    public JobExecutionListener myJobListener() {
        return new MyJobListener();
    }

    /**
     * Listener class for the {@link MyProcessorListener}.
     * @return {@link org.springframework.batch.core.ChunkListener}
     */
    @Bean
    public ChunkListener myProcessorListener() {
        return new MyProcessorListener();
    }



    /**********************
     ****     MISC     ****
     **********************/

    /**
     * This tasklet is used for housekeeping functions.
     *
     * @return {@link org.springframework.batch.core.step.tasklet.Tasklet}
     */
    @Bean
    public Tasklet housekeepingTasklet() {
        return new HousekeepingTasklet();
    }

    /**
     * Run Id incrementer for the Spring Batch jobs.
     * @return @{link RunIdIncrementer}
     */
    @Bean
    public RunIdIncrementer runIdIncrementer() {
        return new RunIdIncrementer();
    }

    /**
     * Returns an instance of SpringBatchApplicationScheduler.
     * @return {@link SpringBatchApplicationScheduler}
     */
    @Bean
    public SpringBatchApplicationScheduler springBatchApplicationScheduler() {
        return new SpringBatchApplicationScheduler();
    }

}
