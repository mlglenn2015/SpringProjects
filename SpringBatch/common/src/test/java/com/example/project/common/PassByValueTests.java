package com.example.project.common;

import com.example.project.common.config.TestDataConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Miscellaneous JUnit tests.
 *
 * @author mlglenn on 12/15/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfiguration.class})
@ActiveProfiles("test")
public class PassByValueTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassByValueTests.class);


    @Before
    public void setUp() {
        LOGGER.debug("PassByValueTests.setUp(): -----> CREATE <-----");
    }

    @After
    public void tearDown() {
        LOGGER.debug("PassByValueTests.tearDown(): -----> DESTROY <-----");
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("PassByValueTests.defaultTest()");
    }

    //Test that proves Java uses "pass by value" arguments
    @Test
    public void passByValueHashMapTest() {
        LOGGER.debug("************************* PassByValueTests.passByValueHashMapTest() ******************************");
        Map<String, String> stringMap = new HashMap<>();
        LOGGER.debug("Step 1: stringMap.hashCode(): {}", stringMap.hashCode());
        stringMap.put("String1Key", "String1Value");
        LOGGER.debug("Step 2: stringMap: {}", stringMap.toString());

        addValuesToHashMap(stringMap);

        LOGGER.debug("Step 5: stringMap.hashCode(): {}", stringMap.hashCode());
        LOGGER.debug("Step 6: stringMap: {}", stringMap.toString());
    }

    private void addValuesToHashMap(Map<String, String> stringMap) {
        LOGGER.debug("Step 3: stringMap: {}", stringMap);
        stringMap.put("String2Key", "String2Value");
        LOGGER.debug("Step 4: stringMap: {}", stringMap.toString());
    }
}
