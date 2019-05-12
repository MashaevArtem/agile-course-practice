package ru.unn.agile.RadixSorter.viewmodel;

import ru.unn.agile.RadixSorter.viewmodel.ViewModel.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getElArray());
        assertEquals("", viewModel.getSortedArrayStringRepres());
        assertEquals("", viewModel.getInputArrayStringRepres());
        assertEquals(Status.WAITING, viewModel.getCurrState());
    }

    @Test
    public void canAddOneElementToArray() {
        viewModel.setInputVal("3");
        viewModel.addProcess();

        assertEquals("[3]", viewModel.getInputArrayStringRepres());
    }

    @Test
    public void canAddSeveralElementsToArray() {
        viewModel.setInputVal("-5");
        viewModel.addProcess();
        viewModel.setInputVal("4");
        viewModel.addProcess();
        viewModel.setInputVal("2");
        viewModel.addProcess();

        assertEquals("[-5, 4, 2]", viewModel.getInputArrayStringRepres());
    }

    @Test
    public void canClearArray() {
        viewModel.setInputVal("7");
        viewModel.addProcess();
        viewModel.setInputVal("5");
        viewModel.addProcess();
        viewModel.setInputVal("2");
        viewModel.addProcess();
        viewModel.setInputVal("11");
        viewModel.addProcess();
        viewModel.setInputVal("9");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals("[]", viewModel.getSortedArrayStringRepres());
    }

    @Test
    public void  isWaitingStatusWithLaunch() {
        assertEquals(Status.WAITING, viewModel.getCurrState());
    }

    @Test
    public void isWaitingStatWithBeginning() {
        assertEquals(ViewModel.Status.WAITING, viewModel.getCurrState());
    }

    @Test
    public void isWaitingStateWithAddAndDelElemEmptyField() {
        viewModel.setInputVal("");

        viewModel.processingAddField(1);

        assertEquals(Status.WAITING, viewModel.getCurrState());
    }

    @Test
    public void isReadyStateWithAddElemFieldIsWriteIn() {
        viewModel.setInputVal("6");

        viewModel.processingAddField(1);

        assertEquals(Status.READY, viewModel.getCurrState());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setInputVal("b");

        viewModel.processingAddField(1);

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrState());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.setInputVal("-1");
        viewModel.addProcess();
        viewModel.setInputVal("2");
        viewModel.addProcess();
        viewModel.setInputVal("5");

        viewModel.sort();

        assertEquals(Status.SUCCESSFUL, viewModel.getCurrState());
    }

    @Test
    public void isAddButtonDisabledWithLaunch() {
        assertEquals(false, viewModel.isAdButtonEnabled());
    }

    @Test
    public void isClearButtonDisabledWithLaunch() {
        assertEquals(false, viewModel.isCleanButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWithLaunch() {
        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isAdButtonEnabledAddElemFieldIsCorrect() {
        viewModel.setInputVal("1");
        viewModel.processingAddField(1);

        assertEquals(true, viewModel.isAdButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWithAddElemFieldIsEmpty() {
        viewModel.setInputVal("");
        viewModel.processingAddField(1);

        assertEquals(false, viewModel.isAdButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWithAddElemIsInvalid() {
        viewModel.setInputVal("ijijfdf");
        viewModel.processingAddField(1);

        assertEquals(false, viewModel.isAdButtonEnabled());
    }

    @Test
    public void isCleanButtonEnabledWithArrayIsNotEmpty() {
        viewModel.setInputVal("3");
        viewModel.addProcess();
        viewModel.setInputVal("8");
        viewModel.addProcess();

        assertEquals(true, viewModel.isCleanButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWithArrayIsNotEmpty() {
        viewModel.setInputVal("7");
        viewModel.addProcess();
        viewModel.setInputVal("5");
        viewModel.addProcess();

        assertEquals(true, viewModel.isSortButtonEnabled());
    }
    @Test
    public void isClearButtonDisabledWithClearArray() {
        viewModel.setInputVal("2");
        viewModel.addProcess();
        viewModel.setInputVal("7");
        viewModel.addProcess();
        viewModel.setInputVal("5");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isCleanButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWithClearArray() {
        viewModel.setInputVal("7");
        viewModel.addProcess();
        viewModel.setInputVal("3");
        viewModel.addProcess();
        viewModel.setInputVal("2");
        viewModel.addProcess();
        viewModel.setInputVal("6");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isClearButtonAddOneElementEnabled() {
        viewModel.setInputVal("3");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals(true, viewModel.isCleanButtonEnabled());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsCorrect() {
        viewModel.setInputVal("tests");
        viewModel.processingAddField(1);
        viewModel.setInputVal("13");
        viewModel.processingAddField(1);

        assertEquals(Status.READY, viewModel.getCurrState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsInvalid() {
        viewModel.setInputVal("13");
        viewModel.processingAddField(1);
        viewModel.setInputVal("tests");
        viewModel.processingAddField(1);

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsEmpty() {
        viewModel.setInputVal("13");
        viewModel.processingAddField(1);
        viewModel.setInputVal("");
        viewModel.processingAddField(1);

        assertEquals(Status.WAITING, viewModel.getCurrState());
    }

        @Test(expected = IllegalArgumentException.class)
    public void throwsViewModelWithLoggerIsNull() {
        FakeLogger logger = null;

        new ViewModel(logger);
    }

        @Test
    public void canConstructViewModelWithLoggerIsNotNull() {
        FakeLogger logger = new FakeLogger();

        new ViewModel(logger);
    }

        public void isLoggerEmptyWithStartup() {
        final int expected = 0;
        List<String> log = viewModel.getLog();

        assertEquals(expected, log.size());
    }

        @Test
    public void isLogUpdatedWithAddToArray() {
        viewModel.setInputVal("11");
        viewModel.addProcess();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ViewModel.ADD_LOG + viewModel.getElArray() + ".*"));
    }

        @Test
    public void isLogUpdatedWithClearArray() {
        viewModel.clearProcess();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ViewModel.CLEAR_LOG + ".*"));
    }

        @Test
    public void isLogUpdatedWithSortArray() {
        viewModel.setInputVal("11");
        viewModel.addProcess();
        viewModel.setInputVal("-4");
        viewModel.addProcess();
        viewModel.setInputVal("10");
        viewModel.addProcess();
        viewModel.sort();
        List<String> logg = viewModel.getLog();
        String message = logg.get(logg.size() - 1);
        assertTrue(message.matches(".*" + viewModel.getSortedArrayStringRepres() + ".*"));
    }
}
