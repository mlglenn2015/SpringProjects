package com.example.project.common;

import com.example.project.common.config.TestDataConfiguration;
import com.example.project.common.domain.MyDataTransferObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;
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

    /**
     * http://www.javaworld.com/article/2077424/learn-java/does-java-pass-by-reference-or-pass-by-value.html
     *
     * The method successfully alters the value of pnt1, even though it is passed by value; however, a swap
     * of pnt1 and pnt2 fails! This is the major source of confusion. In the main() method, pnt1 and pnt2
     * are nothing more than object references. When you pass pnt1 and pnt2 to the tricky() method, Java
     * passes the references by value just like any other parameter. This means the references passed to the
     * method are actually copies of the original references.
     *
     * Java copies and passes the reference by value, not the object. Thus, method manipulation will alter
     * the objects, since the references point to the original objects. But since the references are copies,
     * swaps will fail. As Figure 2 illustrates, the method references swap, but not the original references.
     * Unfortunately, after a method call, you are left with only the unswapped original references. For a swap
     * to succeed outside of the method call, we need to swap the original references, not the copies.
     *
     * Output:
     * PassByValueTests.setUp(): -----> CREATE <-----
     * PassByValueTests.testTrickySwap()
     * X: 0 Y: 0
     * X: 0 Y: 0
     * X: 100 Y:100
     * X: 0 Y: 0
     * PassByValueTests.tearDown(): -----> DESTROY <-----
     */
    @Test
    public void testTrickySwap() {
        LOGGER.debug("PassByValueTests.testTrickySwap()");

        Point pnt1 = new Point(0,0);
        Point pnt2 = new Point(0,0);

        LOGGER.debug("X: " + pnt1.x + " Y: " +pnt1.y);
        LOGGER.debug("X: " + pnt2.x + " Y: " +pnt2.y);

        trickySwap(pnt1,pnt2);

        LOGGER.debug("X: " + pnt1.x + " Y:" + pnt1.y);
        LOGGER.debug("X: " + pnt2.x + " Y: " +pnt2.y);
    }

    /**
     * http://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
     *
     * Java is always pass-by-value.
     * Now think of what an Object's reference/variable does/is:
     *
     * A variable holds the bits that tell the JVM how to get to the referenced Object in memory (Heap).
     * When passing arguments to a method you ARE NOT passing the reference variable, but a copy of the bits in the
     * reference variable. Something like this: 3bad086a. 3bad086a represents a way to get to the passed object.
     * So you're just passing 3bad086a that it's the value of the reference.
     * You're passing the value of the reference and not the reference itself (and not the object).
     * This value is actually COPIED and given to the method.
     *
     * Output:
     * PassByValueTests.setUp(): -----> CREATE <-----
     * Java passes by value.
     * PassByValueTests.tearDown(): -----> DESTROY <-----
     */
    @Test
    public void passByValueTest() {
        MyDataTransferObject dto = new MyDataTransferObject(1L, "Max", "", "");
        LOGGER.debug("DTO Before switchNames(): {}", dto.getProperty1());

        switchNames(dto);

        LOGGER.debug("DTO after switchNames(): {}", dto.getProperty1());
        if (dto.getProperty1().equals("Max")) { //true
            LOGGER.debug( "Java passes by value." );

        } else if (dto.getProperty1().equals("Fifi")) {
            LOGGER.debug( "Java passes by reference." );
        }
    }

    /**
     * Output:
     * PassByValueTests.setUp(): -----> CREATE <-----
     * ************************* PassByValueTests.passByValueHashMapTest() ******************************
     * Step 1: stringMap.hashCode(): 0
     * Step 2: stringMap: {String1Key=String1Value}
     * Step 3: addValuesToHashMap(): stringMap: {String1Key=String1Value}
     * Step 4: addValuesToHashMap(): stringMap: {String2Key=String2Value, String1Key=String1Value}
     * Step 5: stringMap.hashCode(): -1770536228
     * Step 6: stringMap: {String2Key=String2Value, String1Key=String1Value}
     * PassByValueTests.tearDown(): -----> DESTROY <-----
     */
    @Test
    public void passByValueHashMapTest() {
        LOGGER.debug("************************* PassByValueTests.passByValueHashMapTest() ******************************");
        Map<String, String> stringMap = new HashMap<>();
        LOGGER.debug("Step 1: stringMap.hashCode(): {}", stringMap.hashCode());

        stringMap.put("String1Key", "String1Value");
        LOGGER.debug("Step 2: stringMap.toString() Before call to addValuesToHashMap(): {}", stringMap.toString());

        addValuesToHashMap(stringMap);

        LOGGER.debug("");
        LOGGER.debug("Step 5: stringMap.hashCode(): {}", stringMap.hashCode());
        LOGGER.debug("Step 6: stringMap.toString() After call to addValuesToHashMap(): {}", stringMap.toString());
    }

    /**
     * Output:
     * PassByValueTests.setUp(): -----> CREATE <-----
     * ************************* PassByValueTests.passByValueDtoTest() ******************************
     * My DTO: com.example.project.common.domain.MyDataTransferObject@43cf6ea3
     * addValuesToDto(): My DTO: com.example.project.common.domain.MyDataTransferObject@43cf6ea3
     * My DTO: com.example.project.common.domain.MyDataTransferObject@43cf6ea3
     * PassByValueTests.tearDown(): -----> DESTROY <-----
     */
    @Test
    public void passByValueDtoTest() {
        LOGGER.debug("************************* PassByValueTests.passByValueDtoTest() ******************************");
        MyDataTransferObject dto = new MyDataTransferObject(1L, "string1", "string2", "string3");
        LOGGER.debug("My DTO: {}", dto);

        setValuesToDto(dto);

        LOGGER.debug("My DTO: {}", dto);
    }

    private void switchNames(MyDataTransferObject dto) {
        dto.getProperty1().equals("Max"); // true

        dto = new MyDataTransferObject(2L, "Fifi", "", "");
        LOGGER.debug("DTO within switchNames(): {}", dto.getProperty1());
        dto.getProperty1().equals("Fifi"); // true
    }

    private void trickySwap(Point arg1, Point arg2)
    {
        arg1.x = 100;
        arg1.y = 100;
        Point temp = arg1;
        arg1 = arg2;
        arg2 = temp;
    }

    private void addValuesToHashMap(final Map<String, String> stringMap) {
        LOGGER.debug("");
        LOGGER.debug("Step 3: addValuesToHashMap(): stringMap BEFORE ADDING VALUES: {}", stringMap);
        stringMap.put("String2Key", "String2Value");
        LOGGER.debug("Step 4: addValuesToHashMap().toString() AFTER ADDING VALUES: stringMap: {}", stringMap.toString());
    }

    private void setValuesToDto(MyDataTransferObject dto) {
        LOGGER.debug("");
        LOGGER.debug("addValuesToDto(): My DTO: {}", dto);
        dto.setProperty1("string4");
        dto.setProperty2("string5");
        dto.setProperty3("string6");
    }
}
