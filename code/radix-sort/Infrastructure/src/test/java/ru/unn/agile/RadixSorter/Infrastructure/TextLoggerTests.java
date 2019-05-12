package ru.unn.agile.RadixSorter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;


public class TextLoggerTests {
    private static final String DEFAULT_FILEPATH = "./TextLoggerTests.log";
    private TextLogger textLogger;

    @Before
    public void setUp() {
        textLogger = new TextLogger(DEFAULT_FILEPATH);
    }

    @Test
    public void canMakeLoggerWithFileName() {
        assertNotNull(textLogger);
    }

    @Test
    public void canMakeLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(DEFAULT_FILEPATH));
        } catch (FileNotFoundException e) {
            fail("File " + DEFAULT_FILEPATH + " wasn't found!");
        }
    }

    @Test
    public void canLogMess() {
        String testMessage = "Test message to log";

        textLogger.log(testMessage);

        String message = textLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canLogSeveralMess() {
        String[] mess = {"Test #1", "Test #2", "Test #3", "Test #4"};

        for (int i = 0; i < mess.length; ++i) {
            textLogger.log(mess[i]);
        }

        List<String> actualMess = textLogger.getLog();
        for (int i = 0; i < actualMess.size(); ++i) {
            String message = actualMess.get(i);
            assertTrue(message.matches(".*" + mess[i] + "$"));
        }
    }

    @Test(expected = Test.None.class)
    public void noThrowCtorIfInvalidFilenameIsSpecified() {
        new TextLogger("");
    }

    @Test(expected = Test.None.class)
    public void noThrowToLogIfInvalidFilenameIsSpecified() {
        TextLogger logger = new TextLogger("");
        logger.log("hello");
    }

    @Test(expected = Test.None.class)
    public void noThrowGetLogIfInvalidFilenameIsSpecified() {
        TextLogger logger = new TextLogger("");
        logger.log("hello");
        logger.getLog();
    }
}
